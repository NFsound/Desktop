package presentation.sections.home

import application.SonusApplication
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.ScrollPane
import javafx.scene.layout.*
import javafx.scene.text.TextAlignment
import models.core.Playlist
import presentation.main_views.sides.BottomMenuView
import presentation.presenters.sections.HomePresenter
import presentation.presenters.sections.SectionPresenter
import presentation.styles.Colors
import presentation.styles.sections.AccountViewStyles
import presentation.styles.sections.HomeViewStyles
import presentation.styles.sections.HomeViewStyles.Companion.imagePlaylistStyle
import presentation.styles.sections.HomeViewStyles.Companion.playListStyle
import presentation.styles.sections.HomeViewStyles.Companion.titleLabelStyle
import presentation.styles.sections.NewsViewStyles
import presentation.styles.sides.BottomViewStyles
import presentation.styles.sides.LeftMenuStyles.Companion.iconSize
import tornadofx.*
import utils.IconsProvider
import utils.ImageProvider


class HomeViewImpl : View(), HomeView {

    companion object {
        const val playIconFilePath: String =
            SonusApplication.resourcePath + "/icons/play_icon_path.txt"
        const val pauseIconFilePath: String =
            SonusApplication.resourcePath + "/icons/pause_icon_path.txt"
    }


    override fun renderPopularPlaylists(listOfPlaylist: List<Playlist>) {
        popularPlaylistsBox.children.removeAll { true }
        for (playlist in listOfPlaylist) {
            popularPlaylistsBox.vbox {
                //playlist
                stackpane {
                    addClass(playListStyle)
                    usePrefWidth = true
                    usePrefHeight = true
                    imageview(ImageProvider.getImage(playlist.image)) {
                        addClass(imagePlaylistStyle)
                        fitHeight = HomeViewStyles.prefPlaylistSize.toDouble()
                        fitWidth = HomeViewStyles.prefPlaylistSize.toDouble()
                    }
                    stackpane {
                        alignment = Pos.CENTER
                        val playIcon = svgicon(
                            IconsProvider.getSVGPath(playIconFilePath),
                            size = iconSize,
                            color = Colors.alternativeWhiteColor
                        )
                        val pauseIcon = svgicon(
                            IconsProvider.getSVGPath(pauseIconFilePath),
                            size = iconSize,
                            color = Colors.alternativeWhiteColor
                        )
                        setMouseEnterBackground(pauseIcon)
                        setMouseLeaveBackground(playIcon)
                        setMouseEnterBackground(pauseIcon)
                        setMouseLeaveBackground(playIcon)
                    }
                }
                //name
                alignment = Pos.CENTER
                label (playlist.name){
                    alignment = Pos.CENTER
                    textAlignment = TextAlignment.CENTER
                    isWrapText = true
                    addClass(AccountViewStyles.defaultLabelStyle)
                }
            }
        }
    }

    override fun renderAccountPlaylists(listOfPlaylist: List<Playlist>) {
        TODO("Not yet implemented")
    }

    override var sectionTitle = "Home"

    //views
    lateinit var popularPlaylistsBox: HBox


    override fun setPresenter(presenter: SectionPresenter) {
        homePresenter = presenter as HomePresenter
        homePresenter.onInitialLoad()
    }

    override fun getPresenter(): SectionPresenter {
        return homePresenter
    }

    private lateinit var homePresenter: HomePresenter


    override val root: ScrollPane =
        scrollpane {
            addClass(NewsViewStyles.mainScrollViewStyle)
            isFitToWidth = true
            isFitToHeight = true
            vbox {
                addClass(NewsViewStyles.mainVBoxStyle)
                isFillWidth = true
                isFitToHeight = true

                label("Popular playlists") {
                    addClass(titleLabelStyle)
                }
                scrollpane {
                    addClass(NewsViewStyles.mainScrollViewStyle)
                    isFitToWidth = true
                    setOnScroll {
                        hvalue += it.deltaY / (4 * it.multiplierY)
                    }
                    popularPlaylistsBox = hbox {
                        isFillWidth = true
                        isFillHeight = true
                        usePrefHeight = true
                        addClass(HomeViewStyles.popularHBoxStyle)
                    }
                }
            }
        }


    fun setMouseEnterBackground(icon: SVGIcon) {
        icon.setOnMouseEntered {
            icon.background = Background(
                BackgroundFill(
                    Colors.whiteColor,
                    CornerRadii.EMPTY, Insets.EMPTY
                )
            )
        }
    }

    fun setMouseLeaveBackground(icon: SVGIcon) {
        icon.setOnMouseExited {
            icon.background = Background(
                BackgroundFill(
                    Colors.alternativeWhiteColor,
                    CornerRadii.EMPTY, Insets.EMPTY
                )
            )
        }
    }

    fun onPauseClick(playIcon: SVGIcon, pauseIcon: SVGIcon) {
    }
}