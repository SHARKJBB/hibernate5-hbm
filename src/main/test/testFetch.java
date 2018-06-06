import org.hibernate.Session;
import org.junit.Test;
import tedu.sharkj.pojo.Student;
import tedu.sharkj.util.HibernateUtil;

import java.util.List;

public class testFetch {
    @Test
    public void test01() {
        Session session = null;
        try {
            /**
             * 在xml中默认具有延时加载,会发出3条SQL语句
             * 通过设置<many-to-one fetch="join"/>完成对关联对象的抓取
             */
            session = HibernateUtil.openSession();
            Student stu = (Student) session.load(Student.class, 1);
            System.out.println(stu.getName() + "," + stu.getClassRoom().getName()
                    + "," + stu.getClassRoom().getSpecial().getName());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Test
    public void test02() {
        Session session = null;
        try {
            /**
             * 使用了fetch="join"虽然可以完成关联对象的抓取
             * 但是,如果不需要关联对象,仍然会去抓取,会占用系统内存
             */
            session = HibernateUtil.openSession();
            Student stu = (Student) session.load(Student.class, 2);
            System.out.println(stu.getName());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }
    }
}
