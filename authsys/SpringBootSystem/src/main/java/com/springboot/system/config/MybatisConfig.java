//package com.springboot.system.config;
//
//
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import javax.sql.DataSource;
//
///**
// * Mybatis配置
// * @author fancm
// * @date Oct 29, 2018
// */
//@Configuration
//@MapperScan("com.springboot.*.dao")	// 扫描DAO
//public class MybatisConfig {
//  private static Logger log = LoggerFactory.getLogger(SqlSessionFactory.class);
//
//  @Autowired
//  private DataSource dataSource;
//
//  @Bean
//  public SqlSessionFactory sqlSessionFactory() throws Exception {
//    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//    sessionFactory.setDataSource(dataSource);
//    log.info("1111111");
//    sessionFactory.setTypeAliasesPackage("com.springboot.common.entity");	// 扫描Model
//
//	PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//    log.info("2222222");
//	sessionFactory.setMapperLocations(resolver.getResources("classpath*:**/mapper/*.xml"));	// 扫描映射文件
//
//    return sessionFactory.getObject();
//  }
//}