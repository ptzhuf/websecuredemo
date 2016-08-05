package org.hmzb.xss.download;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by ptzhuf on 2016/8/3.
 */
@Controller
public class DownloadController {
    /**
     * logger.
     */
    private static final Logger LOG = getLogger(DownloadController.class);

    // 遇到文件下载的目录遍历攻击, 应该要通过将文件路径存入数据库, 使得客户端只通过文件的id来请求下载文件. 不应该允许客户端传入filename这样的文件名参数.

    /**
     * 文件下载.
     *
     * @param filename 文件名
     * @param response response
     */
    @RequestMapping("download")
    public void getFile(String filename, HttpServletResponse response) {
        String dir = "e:/test/img/";
        try (FileInputStream fileInputStream = FileUtils.openInputStream(new File(dir + filename))) {

            IOUtils.copy(fileInputStream, response.getOutputStream());

            response.setHeader("content-disposition", "attachment;filename=" + filename);
            response.setCharacterEncoding("utf-8");
            response.flushBuffer();
        } catch (IOException e) {
            LOG.error("", e);
        }

    }
}
