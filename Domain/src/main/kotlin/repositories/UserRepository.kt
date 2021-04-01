package repositories

import models.main.Account

interface UserRepository {

    fun getUserAccount(): Account

    fun login(nickname: String, password: String)



}