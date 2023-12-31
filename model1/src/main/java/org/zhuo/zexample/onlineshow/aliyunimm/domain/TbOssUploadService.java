package org.zhuo.zexample.onlineshow.aliyunimm.domain;

import org.zhuo.zexample.onlineshow.aliyunimm.entity.TbOssUpload;

import java.util.List;

/**
 * oss文件链接表(TbOssUpload)表服务接口
 *
 * @author makejava
 * @since 2023-12-19 16:41:45
 */
public interface TbOssUploadService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TbOssUpload queryById(Integer id);


    /**
     * 新增数据
     *
     * @param tbOssUpload 实例对象
     * @return 实例对象
     */
    TbOssUpload insert(TbOssUpload tbOssUpload);

    /**
     * 修改数据
     *
     * @param tbOssUpload 实例对象
     * @return 实例对象
     */
    TbOssUpload update(TbOssUpload tbOssUpload);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @return 全部数据
     */
    List<TbOssUpload> queryAll();
}
