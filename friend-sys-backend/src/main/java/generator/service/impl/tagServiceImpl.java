package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.tag;
import generator.service.tagService;
import generator.mapper.tagMapper;
import org.springframework.stereotype.Service;

/**
* @author RZ
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2025-07-26 16:12:20
*/
@Service
public class tagServiceImpl extends ServiceImpl<tagMapper, tag>
    implements tagService{

}




