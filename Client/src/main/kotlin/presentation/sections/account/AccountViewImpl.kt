package presentation.sections.account

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.stage.StageStyle
import models.core.Account
import presentation.presenters.sections.AccountPresenter
import presentation.presenters.sections.SectionPresenter
import presentation.styles.sections.AccountViewStyles.Companion.accountStyle
import presentation.styles.sections.AccountViewStyles.Companion.buttonClickedStyle
import presentation.styles.sections.AccountViewStyles.Companion.buttonDefaultStyle
import presentation.styles.sections.AccountViewStyles.Companion.defaultLabelStyle
import presentation.styles.sections.AccountViewStyles.Companion.passwordFieldStyle
import presentation.styles.sections.AccountViewStyles.Companion.userInfoLabelStyle
import presentation.styles.sections.AccountViewStyles.Companion.userInfoMainLabelStyle
import presentation.styles.sides.TopViewStyles
import tornadofx.*
import java.util.concurrent.TimeUnit
import javax.swing.GroupLayout

class AccountViewImpl : View(), AccountView {


    //views
    private lateinit var nickNameLabel: Label
    private lateinit var passwordField: TextField

    override fun printAllUsers(list: List<Account>) {
        for (user in list) {
            root.add(button {
                text = user.nickname
            })
        }
    }

    override fun initializeUserInfo(account: Account) {
        nickNameLabel.text = account.nickname

    }

    override var sectionTitle = "Account"


    override fun filterView(text: String) {
        println("from account $text")
    }

    override fun setPresenter(presenter: SectionPresenter) {
        accountPresenter = presenter as AccountPresenter

    }

    override fun getPresenter(): SectionPresenter {
        return accountPresenter
    }


    private lateinit var accountPresenter: AccountPresenter


    override val root: Parent = vbox {
        addClass(accountStyle)

        hbox {
            label {
                text = "Your nickname:"
                addClass(userInfoLabelStyle)
            }
            nickNameLabel = label {
                text = "NICK"
                addClass(userInfoMainLabelStyle)
            }
        }
        hbox {

            label {
                text = "Your password:"
                alignment = Pos.CENTER_LEFT
                addClass(userInfoLabelStyle)
            }
            alignment = Pos.CENTER_LEFT
            passwordField = passwordfield {
                addClass(passwordFieldStyle)
                alignment = Pos.CENTER
                text = "fvsedgvd"
                isEditable = false
            }
        }
        label {
            text = "Change your password:"
            addClass(defaultLabelStyle)
        }
        vbox {
            hbox {
                passwordfield {
                    addClass(passwordFieldStyle)
                    alignment = Pos.CENTER
                    text = "fvsedgvd"
                }
                padding = insets(4)
            }
            hbox {
                alignment = Pos.CENTER_LEFT
                passwordfield {
                    addClass(passwordFieldStyle)
                    alignment = Pos.CENTER
                    text = "fvsedgvd"
                    padding = insets(4, 100, 4, 4)
                }
                label {
                    isVisible = false
                    text = "ff"
                }
                padding = insets(4)
                button {
                    addClass(buttonDefaultStyle)
                    padding = insets(4)
                    text = "Change password"
                    setOnMouseClicked {
                        Completable.fromAction {
                            addClass(buttonClickedStyle)
                        }.delay(200, TimeUnit.MILLISECONDS)
                            .andThen {
                                removeClass(buttonClickedStyle)
                                Platform.runLater {
                                    openInternalWindow<MessageWindow>()
                                }
                            }.subscribe {}
                    }
                }
            }
        }


    }

    init {

    }
}