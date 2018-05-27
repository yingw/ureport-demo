package cn.wilmar.ureport.report.cache;

import com.bstek.ureport.cache.ReportDefinitionCache;
import com.bstek.ureport.definition.ReportDefinition;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类似 DefaultMemoryReportDefinitionCache，增加 clearCache 方法来清除缓存，用于在后台改了报表没法立即生效的场景。
 * 至于初始化这个和 DefaultMemoryReportDefinitionCache 的关系，参考 com.bstek.ureport.cache.CacheUtils#setApplicationContext
 * @author Yin Guo Wei 2018/5/27.
 */
@Component
public class ErasableMemoryCache implements ReportDefinitionCache {

    private Map<String, ReportDefinition> reportMap = new ConcurrentHashMap<String, ReportDefinition>();

    /**
     * 清除缓存
     */
    public void clearCache() {
        this.reportMap.clear();
    }

    @Override
    public ReportDefinition getReportDefinition(String file) {
        return reportMap.get(file);
    }

    @Override
    public void cacheReportDefinition(String file, ReportDefinition reportDefinition) {
        if (reportMap.containsKey(file)) {
            reportMap.remove(file);
        }
        reportMap.put(file, reportDefinition);
    }
}
