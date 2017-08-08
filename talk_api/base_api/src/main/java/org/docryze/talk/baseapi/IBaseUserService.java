package org.docryze.talk.baseapi;

import org.docryze.talk.talkmodel.table.User;

public interface IBaseUserService {
    User selectUserById(String id);
}
