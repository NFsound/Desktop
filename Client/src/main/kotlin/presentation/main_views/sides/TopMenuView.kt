package presentation.main_views.sides

import application.SonusApplication
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.Priority
import javafx.scene.layout.StackPane
import presentation.main_views.main_window.window_states.CustomState
import presentation.main_views.main_window.window_states.InitialState
import presentation.main_views.main_window.window_states.State
import presentation.main_views.main_window.window_states.WindowState
import presentation.presenters.main.CenterPresenter
import presentation.styles.Colors
import presentation.styles.sides.MainWindowStyles
import presentation.styles.sides.TopViewStyles
import tornadofx.*
import utils.IconsProvider
import java.util.concurrent.TimeUnit


class TopMenuView(): View(), SideView {

    private var xOffset: Double = 0.0
    private var yOffset: Double = 0.0

    private var state: State

    /**
     * shared presenter (общий)
     */
    private lateinit var mainPresenter: CenterPresenter


    //views
    private lateinit var minimizePane: StackPane
    private lateinit var maximizePane: StackPane
    private lateinit var closePane: StackPane

    private var searchText: PublishSubject<String>
    private lateinit var box: Label

    companion object {
        const val searchIconFilePath: String = SonusApplication.resourcePath + "icons/search_icon_path.txt"
        const val minimizeIconFilePath: String = SonusApplication.resourcePath + "icons/minimize_icon_path.txt"
        const val maximizeIconFilePath: String = SonusApplication.resourcePath + "icons/maximize_icon_path.txt"
        const val closeIconFilePath: String = SonusApplication.resourcePath + "icons/close_icon_path.txt"
    }

    init {
        state = CustomState(
            primaryStage,
            InitialState(
                0.0, 0.0, MainWindowStyles.prefWidthMain.toDouble(),
                MainWindowStyles.prefHeightMain.toDouble()
            )
        )
        searchText = PublishSubject.create()
    }

    override val root = hbox {
        hbox {
            addClass(TopViewStyles.topBarSettingsStyle)
        }
        addClass(TopViewStyles.topBarStyle)

        //searchbar
        vbox {
            addClass(TopViewStyles.searchTextFieldWrapperStyle)
            hbox {

                addClass(TopViewStyles.searchTextFieldStyle)
                //icon
                stackpane {
                    addClass(TopViewStyles.searchIconStyle)
                    svgicon(
                        IconsProvider.getSVGPath(searchIconFilePath),
                        size = TopViewStyles.searchIconSize
                    )
                }
                textfield {
                    addClass(TopViewStyles.textFieldStyle)
                    hbox {
                        box = label {
                            this.alignment = Pos.BOTTOM_LEFT
                            addClass(TopViewStyles.searchTextLabelStyle)
                            text = "Search"
                        }
                    }
                    this.textProperty().onChange {
                        searchText.onNext(it)
                    }
                    searchText.subscribeOn(Schedulers.computation())
                        .debounce(500, TimeUnit.MILLISECONDS)
                        .subscribe {
                            onSearchBarTextChanged(it)
                        }
                    searchText
                        .subscribe {
                            manageSearchBarVisibility(it)
                        }
                }

            }
        }

        hbox(alignment = Pos.CENTER_RIGHT) {
            hboxConstraints {
                hGrow = Priority.ALWAYS
                margin = insets(6)
            }
            minimizePane = makeIconStackPane(
                IconsProvider.getSVGPath(minimizeIconFilePath),
                this@TopMenuView::onMinimizeIconClicked
            )
            maximizePane = makeIconStackPane(
                IconsProvider.getSVGPath(maximizeIconFilePath),
                this@TopMenuView::onMaximizeIconClicked
            )
            closePane = makeIconStackPane(
                IconsProvider.getSVGPath(closeIconFilePath),
                this@TopMenuView::onCloseIconClicked
            )
            add(minimizePane)
            add(maximizePane)
            add(closePane)
        }


        setOnMousePressed { event ->
            xOffset = event.sceneX
            yOffset = event.sceneY
        }
        setOnMouseDragged { event ->
            primaryStage.x = event.screenX - xOffset
            primaryStage.y = event.screenY - yOffset
        }

    }

    private fun makeIconStackPane(SVGPath: String, mouseClickListener: () -> Unit): StackPane {
        return stackpane {
            addClass(TopViewStyles.iconWrapperStyle)
            val curIcon = svgicon(
                SVGPath,
                size = TopViewStyles.windowIconSize,
                color = Colors.whiteColor
            )
            setOnMouseEntered {
                addClass(TopViewStyles.iconWrapperSelectedStyle)
                applyCss()
            }
            setOnMouseExited {
                removeClass(TopViewStyles.iconWrapperSelectedStyle)
                addClass(TopViewStyles.iconWrapperStyle)
                applyCss()
            }
            setOnMouseClicked {
                mouseClickListener()
            }
        }
    }


    private fun onSearchBarTextChanged(text: String) {
        mainPresenter.onSearch(text)
    }

    private fun manageSearchBarVisibility(text: String) {
        box.isVisible = text.isEmpty()
    }

    private fun onCloseIconClicked() {
        Platform.exit()
        this.close()
    }

    private fun onMinimizeIconClicked() {
        primaryStage.isIconified = true
    }

    private fun onMaximizeIconClicked() {
        state = state.changeState(WindowState.MAXIMIZED)
    }

    override fun setPresenter(centerPresenter: CenterPresenter) {
        mainPresenter = centerPresenter
    }


}