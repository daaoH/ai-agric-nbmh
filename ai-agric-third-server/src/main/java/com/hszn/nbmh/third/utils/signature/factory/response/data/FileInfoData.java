package com.hszn.nbmh.third.utils.signature.factory.response.data;

/**
 * @author liwei
 * @version 1.0
 * @since 2022-09-07 20:41
 */
public class FileInfoData {
    private String fileId;

    private String name;

    private Long size;

    private Integer status;

    private String downloadUrl;

    private Integer pdfTotalPages;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Integer getPdfTotalPages() {
        return pdfTotalPages;
    }

    public void setPdfTotalPages(Integer pdfTotalPages) {
        this.pdfTotalPages = pdfTotalPages;
    }
}
