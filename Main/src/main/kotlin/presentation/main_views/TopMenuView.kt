package presentation.main_views

import presentation.styles.TopViewStyles
import tornadofx.*
import utils.IconsProvider


class TopMenuView : View() {
    private var xOffset: Double = 0.0
    private var yOffset: Double = 0.0

    companion object {
        const val searchIconFilePath: String = "Main/src/main/resources/icons/search_icon_path.txt"
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