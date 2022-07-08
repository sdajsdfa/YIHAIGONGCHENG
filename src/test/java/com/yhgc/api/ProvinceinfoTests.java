//package com.yhgc.api;
//
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.yhgc.api.entity.Provinceinfo;
//import com.yhgc.api.service.ProvinceinfoService;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//@SpringBootTest
//class ProvinceinfoTests {
//
//    @Resource
//    private ProvinceinfoService provinceinfoService;
//
//    /*
//     * 添加
//     * */
//    @Test
//    public void save(){
//        Provinceinfo provinceinfo=new Provinceinfo();
//        provinceinfo.setCreateTime(new Date());
//        provinceinfo.setProvinceName("12312");
//        provinceinfo.setRemark("2131");
//        System.out.println(provinceinfoService.save(provinceinfo));
//    }
//
//    /*
//     * 批量添加-----查询集合所有数据，批量添加
//     * */
//    @Test
//    public void  testProvinceinfo2bach(){
//        List<Provinceinfo> listuser =   new ArrayList<>();
//        for(int i=0;i<10;i++){
//            Provinceinfo provinceinfo =  new Provinceinfo();
//            provinceinfo.setCreateTime(new Date());
//            provinceinfo.setProvinceName(i+"小红");
//            provinceinfo.setRemark(i+"女");
//            listuser.add(provinceinfo);
//            if(i==20){//验证出错时，有没有添加进去
//                int a=i/0;
//            }
//        }
//        provinceinfoService.saveBatch(listuser);//批量导入
//        System.out.println("成功");
//    }
//
//    /*
//     * 普通查询所有
//     * */
//    @Test
//    public void testUser2Select() {
//        List<Provinceinfo> ProvinceinfoList = provinceinfoService.list(null);
//        for(Provinceinfo provinceinfo:ProvinceinfoList) {
//            System.out.println(provinceinfo);
//        }
//
//    }
//
//    /**
//     * 根据 ID 查询
//     *
//     * @param id 主键ID
//     */
//
//    @Test
//    public void testSelectById() {
//        //根据id查询数据
//        Provinceinfo rovinceinfo = this.provinceinfoService.getById(1);
//        System.out.println("result = " + rovinceinfo);
//    }
//
//    /**
//     * 批量查询（根据ID 批量查询）
//     *
//     * @param idList 主键ID列表(不能为 null 以及 empty)
//     */
//    @Test
//    public void testSelectBatchIds() {
//        //根据id集合批量查询
//        List<Provinceinfo> provinceinfos = provinceinfoService.listByIds(Arrays.asList( "1539430097633714184", "1539430097633714185"));
//        for (Provinceinfo provinceinfo : provinceinfos) {
//            System.out.println(provinceinfo);
//        }
//    }
//
//    /**
//     * 根据 entity 条件，查询一条记录
//     *
//     * @param queryWrapper 实体对象封装操作类（可以为 null）
//     */
//    @Test
//    public void testSelectOne() {
//        QueryWrapper<Provinceinfo> wrapper = new QueryWrapper<Provinceinfo>();
//        wrapper.eq("provinceName", "8小红");
//        wrapper.eq("remark", "8女");
//        //根据条件查询一条数据，如果结果超过一条会报错
//        Provinceinfo rovinceinfo = provinceinfoService.getOne(wrapper);
//        System.out.println(rovinceinfo);
//    }
//
//
//    /**
//     * 根据 Wrapper 条件，查询总记录数
//     *
//     * @param queryWrapper 实体对象封装操作类（可以为 null）
//     */
//    @Test
//    public void testSelectCount() {
//        QueryWrapper<Provinceinfo> wrapper = new QueryWrapper<Provinceinfo>();
//        wrapper.eq("remark", "2131"); //年龄大于23岁
//
//        //根据条件查询数据条数
//        Integer count = provinceinfoService.count(wrapper);
//        System.out.println("count = " + count);
//    }
//
//    @Test
//    public void testSelectPage() {
//        QueryWrapper<Provinceinfo> wrapper = new QueryWrapper<Provinceinfo>();
//        wrapper.eq("remark", "2131"); //年龄大于20岁
//
//        //current:当前页  size：没页条数
//        Page<Provinceinfo> page = new Page<>(1,2);
//
//        //根据条件查询数据
//        IPage<Provinceinfo> iPage = provinceinfoService.page(page, wrapper);
//        System.out.println("数据总条数：" + iPage.getTotal());
//        System.out.println("总页数：" + iPage.getPages());
//
//
//        List<Provinceinfo> provinceinfos = iPage.getRecords();
//        for (Provinceinfo provinceinfo : provinceinfos) {
//            System.out.println("provinceinfo = " + provinceinfo);
//        }
//    }
//
//    /*
//     * 简单条件修改Update
//     * */
//    @Test
//    public void testUpdate() {
//        //更新的条件以及字段
//        UpdateWrapper<Provinceinfo> wrapper = new UpdateWrapper<>();
//        wrapper.eq("id", "1").set("remark", "男");
//
//        //执行更新操作
//        System.out.println("result = " + provinceinfoService.update(null, wrapper));
//    }
//
//    /**
//     * 根据 ID 修改
//     *
//     * @param entity 实体对象
//     */
//    @Test
//    public void testUpdateById() {
//        Provinceinfo provinceinfo = new Provinceinfo();
//        provinceinfo.setId("1"); //主键
//        provinceinfo.setRemark("女"); //更新的字段
//
//        //根据id更新，更新不为null的字段
//        provinceinfoService.updateById(provinceinfo);
//    }
//
//
//    /**
//     * 根据 entity 条件，删除记录
//     *
//     * @param wrapper 实体对象封装操作类（可以为 null）
//     */
//    @Test
//    public void testDeleteByMap() {
//        Provinceinfo provinceinfo = new Provinceinfo();
//        provinceinfo.setId("1");
//
//        //将实体对象进行包装，包装为操作条件
//        QueryWrapper<Provinceinfo> wrapper = new QueryWrapper<>(provinceinfo);
//        System.out.println("result = " + provinceinfoService.remove(wrapper));
//    }
//
//    /**
//     * 批量删除（根据ID 批量删除）
//     *
//     * @param idList 主键ID列表(不能为 null 以及 empty)
//     */
//    @Test
//    public void testDelete4() {
//        //根据id集合批量删除
//        System.out.println("result = " + provinceinfoService.removeByIds(Arrays.asList("1539429019680813058","1539145910301249537")));
//    }
//
//
//
//}
