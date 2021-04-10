package presentation.sections.account

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
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
    private lateinit var mainPasswordField: PasswordField
    private lateinit var passwordField1: PasswordField
    private lateinit var passwordField2: PasswordField
    private lateinit var accountUI: VBox
    private lateinit var registerUI: VBox
    private lateinit var nickTextField: TextField
    private lateinit var emailTextField: TextField
    private lateinit var registerPasswordTextField: PasswordField
    private lateinit var confirmPasswordTextField: PasswordField

    override fun printAllUsers(list: List<Account>) {
        for (user in list) {
            root.add(button {
                text = user.nickname
            })
        }
    }

    override fun initializeUserInfo(account: Account) {
        nickNameLabel.text = account.nickname
        mainPasswordField.text = account.password
    }

    override fun hideAccountUI() {
        accountUI.isVisible = false
        registerUI.isVisible = true
    }

    override fun showAccountUI() {
        accountUI.isVisible = true
        registerUI.isVisible = false
    }

    override var sectionTitle = "Account"


    override fun filterView(text: String) {
        println("from account $text")
    }

    override fun setPresenter(presenter: SectionPresenter) {
        accountPresenter = presenter as AccountPresenter
        accountPresenter.onInitialLoad()
    }

    override fun getPresenter(): SectionPresenter {
        return accountPresenter
    }


    private lateinit var accountPresenter: AccountPresenter


    override val root: Parent = stackpane {
        accountUI = vbox {

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
                mainPasswordField = passwordfield {
                    addClass(passwordFieldStyle)
                    alignment = Pos.CENTER
                    isEditable = false
                }
            }


            label {
                text = "Change your password:"
                addClass(userInfoLabelStyle)
            }

            //changing password block
            vbox {
                label("Print your new password") {
                    addClass(defaultLabelStyle)
                }

                hbox {
                    passwordField1 = passwordfield {
                        addClass(passwordFieldStyle)
                        alignment = Pos.CENTER
                    }
                    padding = insets(4)
                }
                label("Repeat your new password") {
                    addClass(defaultLabelStyle)
                }

                hbox {
                    padding = insets(4)
                    alignment = Pos.CENTER_LEFT


                    passwordField2 = passwordfield {
                        addClass(passwordFieldStyle)
                        alignment = Pos.CENTER
                        padding = insets(4, 4, 4, 4)
                    }


                    label {
                        isVisible = false
                        text = "ff"
                    }

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
                                        openInternalWindow(
                                            MessageWindow("Your password is changed"),
                                            owner = this.parent.parent.parent.parent.parent
                                        )
                                    }
                                }.subscribe {}
                        }
                    }

                }
            }

            button {
                addClass(buttonDefaultStyle)
                padding = insets(10)
                text = "Log out"
                setOnMouseClicked {
                    Completable.fromAction {
                        addClass(buttonClickedStyle)
                    }.delay(200, TimeUnit.MILLISECONDS)
                        .andThen {
                            removeClass(buttonClickedStyle)
                            onLogOutButtonClicked()
                        }.subscribe {}
                }
            }
        }




        registerUI = vbox {
            addClass(accountStyle)
            hbox {
                label {
                    text = "Input your nickname:"
                    addClass(userInfoLabelStyle)
                    alignment = Pos.CENTER_LEFT
                }
                alignment = Pos.CENTER_LEFT
                nickTextField = textfield {
                    addClass(passwordFieldStyle)
                    alignment = Pos.CENTER
                }
            }

            hbox {
                label {
                    text = "Input your email:"
                    addClass(userInfoLabelStyle)
                    alignment = Pos.CENTER_LEFT
                }
                alignment = Pos.CENTER_LEFT
                emailTextField = textfield {
                    addClass(passwordFieldStyle)
                    alignment = Pos.CENTER
                }
            }

            hbox {
                label {
                    text = "Input your password:"
                    addClass(userInfoLabelStyle)
                }
                alignment = Pos.CENTER_LEFT
                registerPasswordTextField = passwordfield {
                    addClass(passwordFieldStyle)

                }
            }

            hbox {
                label {
                    text = "Repeat your password:"
                    addClass(userInfoLabelStyle)
                }
                alignment = Pos.CENTER_LEFT
                confirmPasswordTextField = passwordfield {
                    addClass(passwordFieldStyle)
                    alignment = Pos.BOTTOM_CENTER
                }
            }
            hbox {
                button {
                    addClass(buttonDefaultStyle)
                    padding = insets(20)
                    text = "Register"
                    setOnMouseClicked {
                        Completable.fromAction {
                            addClass(buttonClickedStyle)
                        }.delay(200, TimeUnit.MILLISECONDS)
                            .andThen {
                                removeClass(buttonClickedStyle)
                                onRegisterButtonClicked()
                            }.subscribe {}
                    }
                }
                label("Or") {
                    addClass(defaultLabelStyle)
                }
                button {
                    addClass(buttonDefaultStyle)
                    padding = insets(20)
                    text = "Log in"
                    setOnMouseClicked {
                        Completable.fromAction {
                            addClass(buttonClickedStyle)
                        }.delay(200, TimeUnit.MILLISECONDS)
                            .andThen {
                                removeClass(buttonClickedStyle)
                                onLogInButtonClicked()
                            }.subscribe {}
                    }
                }
            }

        }

    }

    fun onRegisterButtonClicked(){
        accountPresenter.registerAccount(nickNameLabel.text,
            emailTextField.text,
            registerPasswordTextField.text
        )
    }

    fun onLogOutButtonClicked(){
        accountPresenter.logOut()
    }

    fun onLogInButtonClicked(){
        accountPresenter.logIn(nickNameLabel.text,
            emailTextField.text,
            registerPasswordTextField.text)
    }

}