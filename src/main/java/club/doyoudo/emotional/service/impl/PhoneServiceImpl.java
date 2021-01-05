package club.doyoudo.emotional.service.impl;

import club.doyoudo.emotional.mapper.PhoneMapper;
import club.doyoudo.emotional.mapper.ProfileMapper;
import club.doyoudo.emotional.model.Phone;
import club.doyoudo.emotional.model.PhoneExample;
import club.doyoudo.emotional.model.Profile;
import club.doyoudo.emotional.model.ProfileExample;
import club.doyoudo.emotional.pojo.ResponseWrapper;
import club.doyoudo.emotional.service.AnalysisService;
import club.doyoudo.emotional.service.PhoneService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PhoneServiceImpl implements PhoneService {
    private PhoneMapper phoneMapper;
    private ProfileMapper profileMapper;
    @Resource
    AnalysisService analysisServiceImpl;

    @Autowired(required = false)
    public PhoneServiceImpl(PhoneMapper phoneMapper, ProfileMapper profileMapper) {
        this.phoneMapper = phoneMapper;
        this.profileMapper = profileMapper;
    }

    @Override
    public ResponseWrapper insertPhone(Phone phone) {
        String uuid = UUID.randomUUID().toString().replaceAll( "-", "" );
        phone.setId( uuid );
        LocalDateTime time = LocalDateTime.now();
        phone.setReleaseTime( time );
        int i = phoneMapper.insertSelective( phone );
        if (i == 1) {
            return new ResponseWrapper( true, 200, "新增成功！", uuid );
        } else {
            return new ResponseWrapper( false, 500, "新增失败，请稍后重试！", null );
        }
    }

    @Override
    public ResponseWrapper updatePhone(Phone phone) {
        int i = phoneMapper.updateByPrimaryKeySelective( phone );
        if (i == 1) {
            return new ResponseWrapper( true, 200, "成功！", null );
        } else {
            return new ResponseWrapper( false, 500, "失败，请稍后重试！", null );
        }
    }

    @Override
    public int updatePhoneCommentNum(String id, boolean flag) {
        Phone p = phoneMapper.selectByPrimaryKey( id );
        Phone phone = new Phone();
        phone.setId( p.getId() );
        if (flag) {
            phone.setCommentNum( p.getCommentNum() + 1 );
        } else {
            phone.setCommentNum( p.getCommentNum() - 1 );
        }
        return phoneMapper.updateByPrimaryKeySelective( phone );
    }

    @Override
    public PageInfo<Phone> searchPhone(String search, Integer pageNum, Integer pageSize) {
        PhoneExample phoneExample = new PhoneExample();

        //现根据作者昵称 模糊查找作者 获取作者id
        List<String> authorIdList = null;
        if (search != null && !search.equals( "" )) {
            ProfileExample profileExample = new ProfileExample();
            profileExample.createCriteria().andNicknameLike( "%" + search + "%" );
            List<Profile> profileList = profileMapper.selectByExample( profileExample );
            //Java8：从List中获取所有对象的某一个属性构成另一个集合
            authorIdList = profileList.stream().map( Profile::getUserId ).collect( Collectors.toList() );
        }

        phoneExample.setOrderByClause( "comment_num desc" );

        //分页：只对其后的第一个查询有效
        Page<Phone> page = PageHelper.startPage( pageNum, pageSize );
        List<Phone> phones = phoneMapper.selectByExample( phoneExample );
        PageInfo<Phone> pageInfo = page.toPageInfo();
        //封装phones为phoneList
        Iterator<Phone> it = phones.iterator();
        List<Phone> phoneList = new ArrayList<>();
        //插入位置控制
        while (it.hasNext()) {
            Phone phone = it.next();
            phoneList.add( phone );
        }
        pageInfo.setList( phoneList );
        return pageInfo;
    }

    @Override
    public ResponseWrapper selectPhoneById(String phoneId) {
        Phone phone = phoneMapper.selectByPrimaryKey( phoneId );

        return new ResponseWrapper( true, 200, "查询完成！", phone );
    }

    @Override
    public Phone selectPhoneById1(String phoneId) {
        return phoneMapper.selectByPrimaryKey( phoneId );
    }

    @Override
    public List<Phone> selectPhoneListByExample(PhoneExample phoneExample) {
        return phoneMapper.selectByExample( phoneExample );
    }

    @Override
    public List<Phone> selectPhoneListById(List<String> phoneIdList) {
        List<Phone> phoneList = phoneIdList.stream().map( id -> phoneMapper.selectByPrimaryKey( id ) ).collect( Collectors.toList() );
        phoneList.sort( (o1, o2) -> o2.getReleaseTime().compareTo( o1.getReleaseTime() ) );
        return phoneList;
    }

}
