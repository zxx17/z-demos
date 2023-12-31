create table tb_oss_upload
(
    id           int auto_increment comment '主键'
        primary key,
    file_url     varchar(256) null comment 'oss文件路径',
    imm_req_json varchar(512) null comment 'imm自定义请求数据',
    created_time timestamp    null comment '上传时间',
    is_deleted   int          null comment '是否删除 0否 1是'
)
    comment 'oss文件链接表';