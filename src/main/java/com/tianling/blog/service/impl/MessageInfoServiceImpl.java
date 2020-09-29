package com.tianling.blog.service.impl;

import com.tianling.blog.beans.MessageInfo;
import com.tianling.blog.dao.MessageInfoMapper;
import com.tianling.blog.service.MessageInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tianling
 * @since 2020-05-08
 */
@Service
public class MessageInfoServiceImpl extends ServiceImpl<MessageInfoMapper, MessageInfo> implements MessageInfoService {

}
