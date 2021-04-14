package presentation.sections.account

import models.core.account.Account
import presentation.sections.common.SectionView

interface AccountView: SectionView {

    fun initializeUserInfo(account: Account)

    fun hideAccountUI()

    fun showAccountUI()


}