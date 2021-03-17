package presentation.main_views

import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.layout.Priority
import javafx.scene.layout.StackPane
import presentation.styles.Colors
import presentation.styles.TopViewStyles
import tornadofx.*
import utils.IconsProvider


class TopMenuView : View() {
    private var xOffset: Double = 0.0
    private var yOffset: Double = 0.0

    companion object {
        const val searchIconFilePath: String = "Main/src/main/resources/icons/search_icon_path.txt"
        const val minimizeIconFilePath: String = "Main/src/main/resources/icons/minimize_icon_path.txt"
        const val maximizeIconFilePath: String = "Main/src/main/resources/icons/maximize_icon_path.txt"
        const val closeIconFilePath: String = "Main/src/main/resources/icons/close_icon_path.txt"
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
            stackpane {
                addClass(TopViewStyles.iconWrapperStyle)
                svgicon(
                    IconsProvider.getSVGPath(minimizeIconFilePath),
                    size = TopViewStyles.windowIconSize,
                    color = Colors.whiteColor
                )
            }
            stackpane {
                addClass(TopViewStyles.iconWrapperStyle)
                svgicon(
                    IconsProvider.getSVGPath(maximizeIconFilePath),
                    size = TopViewStyles.windowIconSize,
                    color = Colors.whiteColor
                )
            }
            stackpane {
                addClass(TopViewStyles.iconWrapperStyle)
                val closeIcon = svgicon(
                    IconsProvider.getSVGPath(closeIconFilePath),
                    size = TopViewStyles.windowIconSize,
                    color = Colors.whiteColor
                )
                setOnMouseEntered{
                    addClass(TopViewStyles.iconWrapperSelectedStyle)
                    applyCss()
                }
                setOnMouseExited {
                    removeClass(TopViewStyles.iconWrapperSelectedStyle)
                    addClass(TopViewStyles.iconWrapperStyle)
                    applyCss()
                }
            }
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
}