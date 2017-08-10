package ua.kyiv.univerpulse.studentv2.mvc.domain;

public class FileInfo {

    private String originalName;
    private String uploadName;
    private Long size;

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getUploadName() {
        return uploadName;
    }

    public void setUploadName(String uploadName) {
        this.uploadName = uploadName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) { this.size = size; }

    @Override
    public String toString() {
        return "FileInfo{" +
                "originalName='" + originalName + '\'' +
                ", uploadName='" + uploadName + '\'' +
                ", size=" + size +
                '}';
    }

}
