package presentation.main_views

import application.SonusApplication
import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import javafx.scene.layout.StackPane
import presentation.main_views.states.CustomState
import presentation.main_views.states.InitialState
import presentation.main_views.states.State
import presentation.main_views.states.WindowState
import presentation.styles.Colors
import presentation.styles.MainWindowStyles
import presentation.styles.TopViewStyles
import tornadofx.*
import utils.IconsProvider


class TopMenuView : View() {

    private var xOffset: Double = 0.0
    private var yOffset: Double = 0.0

    private var state: State

    private lateinit var minimizePane: StackPane
    private lateinit var maximizePane: StackPane
    private lateinit var closePane: StackPane

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
    }

    override val root = hbox {
        hbox {
            addClass(TopViewStyles.topBarSettingsStyle)
        }
        addClass(TopViewStyles.topBarStyle)
        //searchbar
        vbox {
            addClass(TopViewStyles.searchTextFieldWrapperStyle)
            textfield {

                addClass(TopViewStyles.searchTextFieldStyle)
                hbox {
                    stackpane {
                        addClass(TopViewStyles.searchIconStyle)
                        svgicon(
                            IconsProvider.getSVGPath(searchIconFilePath),
                            size = TopViewStyles.searchIconSize
                        )
                    }
                    label {
                        addClass(TopViewStyles.searchTextLabelStyle)
                        text = "search"
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

    fun onCloseIconClicked() {
        Platform.exit()
    }

    fun onMinimizeIconClicked() {
        primaryStage.isIconified = true
    }

    fun onMaximizeIconClicked() {
        state = state.changeState(WindowState.MAXIMIZED)
    }


}