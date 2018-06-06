import org.hibernate.Session;
import org.junit.Test;
import tedu.sharkj.pojo.Student;
import tedu.sharkj.util.HibernateUtil;

import java.util.List;


public class testSessionCache {
    @Test
    public void test01() {
        //1. 获取Session
        Session session = null;
        try {
            //2. 开启事务
            session = HibernateUtil.openSession();
            //3. 操作
            //session.get的时候，hibernate就将数据库中的数据加载到session缓存中，同时也会备份到快照中
            Student student = session.get(Student.class, 2);
            //第一次查询
            String hql = "from Student where id=1";
            Object student1 = (Student) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("第一次查询：stu = " + student1);

            //第二次查询
            Object student2 = (Student) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("第二次查询：stu = " + student2);

            //将一级缓存数据清空
            session.clear();

            //第三次查询，从二级缓存中读取
            Object student3 = (Student) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("第三次查询：stu = " + student3);
            //4. 事务提交
//            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }

    }

    @Test
    public void test03() {
        //1. 获取Session
        Session session = null;
        try {
            //2. 开启事务
            session = HibernateUtil.openSession();
            //3. 操作
            //session.get的时候，hibernate就将数据库中的数据加载到session缓存中，同时也会备份到快照中

            //第一次查询
            String hql = "select stu from Student stu left join stu.classRoom cla where cla.id=:id";
            List<Student> students = session.createQuery(hql).setParameter("id", 1).setCacheable(true).list();
            for (Student student : students) {
                System.out.println(student.getName() + "," + student.getSex() + "," + student.getClassRoom().getName());
            }
            //第二次查询
            List<Student> students2 = session.createQuery(hql).setParameter("id", 1).setCacheable(true).list();
            for (Student student : students2) {
                System.out.println(student.getName() + "," + student.getSex() + "," + student.getClassRoom().getName());
            }

            //4. 事务提交
//            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }
        try {
            //2. 开启事务
            session = HibernateUtil.openSession();
            //3. 操作
            //session.get的时候，hibernate就将数据库中的数据加载到session缓存中，同时也会备份到快照中

            //第一次查询
            String hql = "select stu from Student stu left join stu.classRoom cla where cla.id=:id";
            List<Student> students = session.createQuery(hql).setParameter("id", 1).setCacheable(true).list();
            for (Student student : students) {
                System.out.println(student.getName() + "," + student.getSex() + "," + student.getClassRoom().getName());
            }
//            //第二次查询
//            List<Student> students2 =  session.createQuery(hql).setParameter("id", 1).setCacheable(true).list();
//            for (Student student : students2) {
//                System.out.println(student.getName() + "," + student.getSex() + "," + student.getClassRoom().getName());
//            }

            //4. 事务提交
//            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Test
    public void test04() {
        //1. 获取Session
        Session session = null;
        try {
            //2. 开启事务
            session = HibernateUtil.openSession();
            //3. 操作
            //session.get的时候，hibernate就将数据库中的数据加载到session缓存中，同时也会备份到快照中
            Student student = session.get(Student.class, 2);
            //第一次查询
            String hql = "from Student where id=1";
            Object student1 = (Student) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("第一次查询：Student = " + ((Student) student1).getName());
            //第二次查询
            Object student2 = (Student) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("第二次查询：Student = " + ((Student) student2).getName());

            //将一级缓存数据清空
            session.clear();

            //第三次查询，从二级缓存中读取
            Object student3 = (Student) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("第三次查询：Student = " + ((Student) student3).getName());
            //4. 事务提交
//            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }
        try {
            //2. 开启事务
            session = HibernateUtil.openSession();
            //3. 操作
            //session.get的时候，hibernate就将数据库中的数据加载到session缓存中，同时也会备份到快照中
            Student student = session.get(Student.class, 2);
            //第一次查询
            String hql = "from Student where id=1";
            Object student1 = (Student) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("第一次查询：Country = " + ((Student) student1).getName());
            //第二次查询
            Object student2 = (Student) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("第二次查询：Country = " + ((Student) student2).getName());

            //将一级缓存数据清空
            session.clear();

            //第三次查询，从二级缓存中读取
            Object student3 = (Student) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("第三次查询：Country = " + ((Student) student3).getName());
            //4. 事务提交
//            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }
    }@Test
    public void test05() {
        //1. 获取Session
        Session session = null;
        try {
            //2. 开启事务
            session = HibernateUtil.openSession();
            //3. 操作
            //session.get的时候，hibernate就将数据库中的数据加载到session缓存中，同时也会备份到快照中
            Student student = session.get(Student.class, 2);
            //第一次查询
            String hql = "from Student where id=1";
            Student student1 =  session.load(Student.class,66).setCacheable(true).uniqueResult();
            System.out.println("第一次查询：Student = " + ((Student) student1).getName());
            //第二次查询
            Object student2 = (Student) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("第二次查询：Student = " + ((Student) student2).getName());

            //将一级缓存数据清空
            session.clear();

            //第三次查询，从二级缓存中读取
            Object student3 = (Student) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("第三次查询：Student = " + ((Student) student3).getName());
            //4. 事务提交
//            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }
        try {
            //2. 开启事务
            session = HibernateUtil.openSession();
            //3. 操作
            //session.get的时候，hibernate就将数据库中的数据加载到session缓存中，同时也会备份到快照中
            Student student = session.get(Student.class, 2);
            //第一次查询
            String hql = "from Student where id=1";
            Object student1 = (Student) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("第一次查询：Country = " + ((Student) student1).getName());
            //第二次查询
            Object student2 = (Student) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("第二次查询：Country = " + ((Student) student2).getName());

            //将一级缓存数据清空
            session.clear();

            //第三次查询，从二级缓存中读取
            Object student3 = (Student) session.createQuery(hql).setCacheable(true).uniqueResult();
            System.out.println("第三次查询：Country = " + ((Student) student3).getName());
            //4. 事务提交
//            session.getTransaction().commit();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            HibernateUtil.close(session);
        }
    }
}
