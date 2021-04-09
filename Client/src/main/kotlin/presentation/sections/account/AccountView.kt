package presentation.sections.account

import models.core.Account
import presentation.sections.SectionView

interface AccountView: SectionView {

    fun printAllUsers(list: List<Account>)

}