package org.zhuo.zexample.onlineshow.aliyunimm.mapper;

import org.apache.ibatis.annotations.Param;

import org.zhuo.zexample.onlineshow.aliyunimm.entity.TbOssUpload;

import java.util.List;

/**
 * oss文件链接表(TbOssUpload)表数据库访问层
 *
 * @author makejava
 * @since 2023-12-19 16:41:43
 */
public interface TbOssUploadDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbOssUpload queryById(Integer id);


    /**
     * 统计总行数
     *
     * @param tbOssUpload 查询条件
     * @return 总行数
     */
    long count(TbOssUpload tbOssUpload);

    /**
     * 新增数据
     *
     * @param tbOssUpload 实例对象
     * @return 影响行数
     */
    int insert(TbOssUpload tbOssUpload);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TbOssUpload> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TbOssUpload> entities);


    /**
     * 修改数据
     *
     * @param tbOssUpload 实例对象
     * @return 影响行数
     */
    int update(TbOssUpload tbOssUpload);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * @return 全部数据
     */
    List<TbOssUpload> queryAll();
}

