package org.zhuo.zother.onlineshow.aliyunimm.domain;

import org.springframework.stereotype.Service;

import org.zhuo.zother.onlineshow.aliyunimm.entity.TbOssUpload;
import org.zhuo.zother.onlineshow.aliyunimm.mapper.TbOssUploadDao;

import javax.annotation.Resource;

/**
 * oss文件链接表(TbOssUpload)表服务实现类
 *
 * @author makejava
 * @since 2023-12-19 16:41:46
 */
@Service("tbOssUploadService")
public class TbOssUploadServiceImpl implements TbOssUploadService {
    @Resource
    private TbOssUploadDao tbOssUploadDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TbOssUpload queryById(Integer id) {
        return this.tbOssUploadDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param tbOssUpload 实例对象
     * @return 实例对象
     */
    @Override
    public TbOssUpload insert(TbOssUpload tbOssUpload) {
        this.tbOssUploadDao.insert(tbOssUpload);
        return tbOssUpload;
    }

    /**
     * 修改数据
     *
     * @param tbOssUpload 实例对象
     * @return 实例对象
     */
    @Override
    public TbOssUpload update(TbOssUpload tbOssUpload) {
        this.tbOssUploadDao.update(tbOssUpload);
        return this.queryById(tbOssUpload.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.tbOssUploadDao.deleteById(id) > 0;
    }
}
