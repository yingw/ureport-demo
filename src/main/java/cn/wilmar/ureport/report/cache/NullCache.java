package cn.wilmar.ureport.report.cache;

import com.bstek.ureport.cache.ReportDefinitionCache;
import com.bstek.ureport.definition.ReportDefinition;
import org.springframework.stereotype.Component;

/**
 * 完全不适用 Cache，建议不开启。如果需要开启，加上类上注解 @Component。
 * （建议使用 ErasableMemoryCache）
 * @author Yin Guo Wei 2018/5/27.
 */
//@Component
public class NullCache implements ReportDefinitionCache {
    @Override
    public ReportDefinition getReportDefinition(String file) {
        System.out.println("NullCache.getReportDefinition");
        System.out.println("file = " + file);
        return null;
    }

    @Override
    public void cacheReportDefinition(String file, ReportDefinition reportDefinition) {
        System.out.println("NullCache.cacheReportDefinition");
    }
}
