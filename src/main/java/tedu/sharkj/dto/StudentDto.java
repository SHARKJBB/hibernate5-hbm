package tedu.sharkj.dto;

public class StudentDto {
    private String name;
    private String cname;
    private String pname;

    @Override
    public String toString() {
        return "StudentDto{" +
                "name='" + name + '\'' +
                ", cname='" + cname + '\'' +
                ", pname='" + pname + '\'' +
                '}';
    }

    public StudentDto(String name, String cname, String pname) {
        this.name = name;
        this.cname = cname;
        this.pname = pname;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
