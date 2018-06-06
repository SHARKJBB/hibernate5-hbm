import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import org.w3c.dom.ls.LSException;
import tedu.sharkj.dto.StudentDto;
import tedu.sharkj.pojo.Special;
import tedu.sharkj.pojo.Student;
import tedu.sharkj.util.HibernateUtil;

import java.util.List;

public class TestHQL {
    @Test
    public void test01() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            /**
             * HQL是基于对象
             * 特别注意:使用HQL查询对象时,要求实体类中必须有一个不带参数的构造方法
             */
            Query query = session.createQuery("from Special");
            List<Special> specials = query.list();
            for (Special spe : specials) {
                System.out.println(spe);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Test
    public void test02() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            /**
             * 不能使用select *查询
             */
            List<Special> specials = session.createQuery("select spe from Special spe").list();
            for (Special special : specials) {
                System.out.println(special);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Test
    public void test03() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            /**
             * 条件查询 --模糊条件查询
             */
            List<Student> stus = session.createQuery
                    ("from Student where name like '%张%'").list();
            for (Student stu : stus) {
                System.out.println(stu.getName());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Test
    public void test04() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            /**
             * 条件查询---基于占位符的查询，在替换占位符时JDBC从1开始，hibnate从0开始
             * 使用?查询的时候,在替换占位符的时候,JDBC从1开始,hibernate从0开始
             */
            List<Student> students = session.createQuery("from Student where name like'张%' ").list();
            List<Student> students2 = session.createQuery("from Student where name like ? ").setParameter(0, "张%").list();

            for (Student student : students) {
                System.out.println(student);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Test
    public void test05() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            /**
             * 条件查询---基于别名的查询
             * 替换的时候没有顺序
             */
            List<Student> students2 = session.createQuery("from Student where name like :name and sex=:sex").
                    setParameter("name", "李%").setParameter("sex", "男 ").list();
            for (Student student : students2) {
                System.out.println(student);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Test
    public void testUniqueResult06() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            /**
             * 条件查询---基于别名的查询，
             */
            Long student = (Long) session.createQuery("select count(*) from Student where name like :name and sex=:sex").
                    setParameter("name", "李%").setParameter("sex", "男 ").uniqueResult();
            System.out.println(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Test
    public void testUniqueResult07() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            /**
             * 条件查询---基于别名的查询，
             */
            Student student = (Student) session.createQuery("from Student where id=:id").setParameter("id", 66).uniqueResult();
            System.out.println(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Test
    public void test08() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            /**
             * 基于投影的查询，会将返回数据封装在数组里
             */
            List<Object[]> objects = session.createQuery("select stu.sex,count(*) from Student stu group by stu.sex").list();
            for (Object[] object : objects) {
                System.out.println(object[1] + "   " + object[0]);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Test
    public void test10() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            /**
             * setParameterList可以替代一组数据
             */
            List<Student> students = session.createQuery("select stu from Student stu where stu.classRoom.id in (:class)")
                    .setParameterList("class", new Integer[]{1, 2}).list();
            for (Student student : students) {
                System.out.println(student.getName() + "   " + student.getClassRoom());
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Test
    public void Limit11() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            /**
             * setParameterList可以替代一组数据
             */
            List<Student> students = session.createQuery("from Student stu where stu.classRoom is null").list();
            for (Student student : students) {
                System.out.println(student.getName() + "   " + student.getClassRoom());
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Test
    public void limit11() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            /**
             * 分页查询必须在所有参数都赋值以后操作
             */
            List<Student> students = session.createQuery
                    ("select stu from Student stu where stu.classRoom.id " +
                            "in(:cla)")
                    .setParameterList("cla", new Integer[]{1, 2})
                    .setFirstResult(0).setMaxResults(10)
                    .list();
            for (Student student : students) {
                System.out.println(student.getName() + "    " + student.getSex() + "    " + student.getClassRoom().getName());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Test
    public void isNull12() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            /**
             *
             */
            List<Student> students = session.createQuery
                    ("select stu from Student stu where stu.classRoom is null")
                    .list();
            for (Student student : students) {
                System.out.println(student.getName() + "    " + student.getSex());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Test
    public void LeftJoin13() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            /**
             *
             */
            List<Student> students = session.createQuery
                    ("select stu from Student stu left join " +
                            "stu.classRoom cla where cla.id=:id")
                    .setParameter("id", 1)
                    .list();
            for (Student student : students) {
                System.out.println(student.getName() + "," + student.getSex() + "," + student.getClassRoom());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Test
    public void LeftJoinT14() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            /**
             *
             */
            List<Object[]> stus = session.createQuery
                    ("select stu.name,stu.sex,cla.name,spe.name" +
                            " from Student stu left join " +
                            "stu.classRoom cla left join cla.special spe" +
                            " where cla.id=:id")
                    .setParameter("id", 1)
                    .list();
            for (Object[] stu : stus) {
                System.out.println(stu[0] + "," + stu[1] + "," + stu[2] + "," + stu[3]);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }

    }

    /**
     * DTO:Data Transfer Object(数据传输对象)
     * 没有存储功能,只是用来做数据的传输
     */
    @Test
    public void Dto15() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            /**
             * setParameterList可以替代一组数据
             */
            List<StudentDto> studentDtos = session.createQuery("select new tedu.sharkj.dto.StudentDto(stu.name,cla.name as cname,spe.name as pname)  from Student stu left join stu.classRoom cla left join cla.special spe where cla.id=:id").setParameter("id", 1).list();
            for (StudentDto studentDto : studentDtos) {
                System.out.println(studentDto);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }
}

