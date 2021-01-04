package club.doyoudo.emotional.service.impl;

import club.doyoudo.emotional.mapper.ProfileMapper;
import club.doyoudo.emotional.model.Profile;
import club.doyoudo.emotional.model.ProfileExample;
import club.doyoudo.emotional.pojo.ResponseWrapper;
import club.doyoudo.emotional.service.ProfileService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileMapper profileMapper;

    @Autowired(required = false)
    public ProfileServiceImpl(ProfileMapper profileMapper) {
        this.profileMapper = profileMapper;
    }


    @Override
    public int insertProfile(Profile profile) {
        return profileMapper.insertSelective( profile );
    }

    @Override
    public String selectAvatar(String userId) {
        Profile profile = profileMapper.selectByPrimaryKey( userId );
        return profile.getAvatar();
    }

    @Override
    public ResponseWrapper updateProfile(Profile profile) {
        int i = profileMapper.updateByPrimaryKeySelective( profile );
        if (i == 1) {
            return new ResponseWrapper( true, 200, "修改成功！", null );
        } else {
            return new ResponseWrapper( false, 500, "修改失败，请稍后重试！", null );
        }
    }

    @Override
    public ResponseWrapper selectProfile(String userId) {
        Profile profile = profileMapper.selectByPrimaryKey( userId );
        return new ResponseWrapper( true, 200, "成功！", profile );
    }

    @Override
    public PageInfo<Profile> searchUserList(String search, Integer pageNum, Integer pageSize) {
        ProfileExample profileExample = new ProfileExample();
        profileExample.createCriteria().andNicknameLike( "%" + search + "%" );

        Page<Profile> page = PageHelper.startPage( pageNum, pageSize );
        profileMapper.selectByExample( profileExample );
        PageInfo<Profile> pageInfo = page.toPageInfo();
        return pageInfo;
    }

    @Override
    public Profile selectProfile1(String userId) {
        return profileMapper.selectByPrimaryKey( userId );
    }

    @Override
    public ResponseWrapper validateNickName(String nickName) {
        ProfileExample profileExample = new ProfileExample();
        profileExample.createCriteria().andNicknameEqualTo( nickName );
        List<Profile> list = profileMapper.selectByExample( profileExample );
        if (list != null && list.size() > 0) {
            return new ResponseWrapper( false, 200, "该昵称已经存在！", null );
        } else {
            return new ResponseWrapper( true, 200, "该昵称可以使用！", null );
        }
    }


}
