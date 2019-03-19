# UReport Demo

[![Build Status](https://travis-ci.org/yingw/ureport-demo.svg?branch=master)](https://travis-ci.org/yingw/ureport-demo)
[![Apache 2.0](https://img.shields.io/github/license/micrometer-metrics/micrometer.svg)](http://www.apache.org/licenses/LICENSE-2.0)

此项目是 [UReport](https://github.com/youseries/ureport/) 产品和 Spring Boot 整合后的一个实例

- Spring Boot 集成
- 报表可保存至数据库，JpaReportProvider
- 一个简单的 Web UI

## 使用

下载源码
```
git clone https://github.com/yingw/ureport-demo.git --depth=1
```

直接运行
```
cd ureport-demo & mvn spring-boot:run
```

或者打包后运行
```
mvn package & cd target & java -jar ureport-demo-1.0.0.jar
```

启动成功后，访问：http://localhost:8080

> 注：默认设置下会在 Tomcat 的临时目录保存报表，并且在第一次运行时会生成一个 User 数据库（h2 数据库）并生成一个 `sample.ureport.xml` 的报表。

查看数据库中的 Report，访问：http://localhost:8080/h2-console

## 设置

如果不需要那个 sample 报表，只需要将 UreportDemoApplication 类中的 `reportService.createSampleReport();` 删掉即可。

如果要在具体路径保存报表，修改 `config.properties` 内的 `ureport.fileStoreDir`

如果也有需要在数据库、文件修改报表 xml 内容，发现预览不同步，可以使用删除缓存功能，提交 post 请求到： http://localhost:8080/clearCache 即可。

## 系统截图

首页
<img src="docs/report_list.png" style="width: 1024px">

样例报表预览
<img src="docs/sample_preview.png" style="width: 480px">

## 问题

已知问题：
- UI 上删除数据库报表不会清除缓存，可继续 Preview (FIXed)
- 部分 IE 浏览器 Preview 不可用，建议使用 Chrome
- 偶尔不 Preview 时直接导出没有图表，Preview 即可，原因未知

其他问题请前往 https://github.com/yingw/ureport-demo/issues 提 issue，谢谢。

## 版本历史

- 2018-04-22 v1.0.0 初次版本
- 2018-05-27 v1.0.1 更新 UReport 2.2.8，增加缓存同步功能 