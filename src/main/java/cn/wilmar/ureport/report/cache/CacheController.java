package cn.wilmar.ureport.report.cache;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 接收前端发来 post 指令清除缓存
 * @author Yin Guo Wei 2018/5/27.
 */
@Controller
public class CacheController {

    private final ErasableMemoryCache erasableMemoryCache;

    public CacheController(ErasableMemoryCache erasableMemoryCache) {
        this.erasableMemoryCache = erasableMemoryCache;
    }

    /**
     * 清除缓存的入口
     * @return 返回首页
     */
    @PostMapping("/clearCache")
    public String clearCache() {
        erasableMemoryCache.clearCache();
        return "redirect:/reports";
    }
}
