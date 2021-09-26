package com.finch.god.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "SqlSessionFactory")
public class DataSourceConfig {

    static final String PACKAGE = "com.finch.god.common.dao";
    static final String MAPPER_LOCATION = "classpath:mapper/*.xml";

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClass;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.type}")
    private String type;

    @Value("${spring.datasource.name}")
    private String name;

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() {
        DruidDataSource masterDataSource = new DruidDataSource();
        masterDataSource.setDriverClassName(driverClass);
        masterDataSource.setUrl(url);
        masterDataSource.setUsername(user);
        masterDataSource.setPassword(password);
        masterDataSource.setMinIdle(minIdle);
        masterDataSource.setMaxActive(maxActive);
        masterDataSource.setInitialSize(initialSize);
        masterDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        masterDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        masterDataSource.setTestWhileIdle(testWhileIdle);
        masterDataSource.setTestOnBorrow(testOnBorrow);
        masterDataSource.setTestOnReturn(testOnReturn);
        masterDataSource.setDbType(type);
        masterDataSource.setName(name);
        return masterDataSource;
    }

    @Bean(name = "SqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);

        //注入分页组件
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        sessionFactory.setPlugins(new Interceptor[]{pageHelper});
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

}
