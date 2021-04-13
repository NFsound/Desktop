package presentation.sections.home

import application.SonusApplication
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.ScrollPane
import javafx.scene.control.skin.ScrollPaneSkin
import javafx.scene.layout.*
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import models.core.Playlist
import presentation.presenters.sections.HomePresenter
import presentation.presenters.sections.SectionPresenter
import presentation.sections.music.PlaylistCreateMessage
import presentation.styles.Colors
import presentation.styles.sections.AccountViewStyles
import presentation.styles.sections.HomeViewStyles
import presentation.styles.sections.HomeViewStyles.Companion.blackSmokeStyle
import presentation.styles.sections.HomeViewStyles.Companion.imagePlaylistStyle
import presentation.styles.sections.HomeViewStyles.Companion.playListStyle
import presentation.styles.sections.HomeViewStyles.Companion.playlistLabelStyle
import presentation.styles.sections.HomeViewStyles.Companion.titleLabelStyle
import presentation.styles.sections.NewsViewStyles
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
            popularPlaylistsBox.createOnePlaylistBox(playlist)
        }
    }





    fun HBox.createOnePlaylistBox(playlist: Playlist): VBox {
        return vbox {
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
                vbox {
                    addClass(blackSmokeStyle)
                    prefHeight = HomeViewStyles.prefPlaylistSize.toDouble()
                    prefWidth = HomeViewStyles.prefPlaylistSize.toDouble()
                }
                stackpane {
                    alignment = Pos.CENTER
                    val pauseIcon = svgicon(
                        IconsProvider.getSVGPath(pauseIconFilePath),
                        size = iconSize,
                        color = Colors.alternativeWhiteColor
                    )
                    val playIcon = svgicon(
                        IconsProvider.getSVGPath(playIconFilePath),
                        size = iconSize,
                        color = Colors.alternativeWhiteColor
                    )
                    setMouseEnterBackground(pauseIcon)
                    setMouseLeaveBackground(pauseIcon)
                    setMouseEnterBackground(playIcon)
                    setMouseLeaveBackground(playIcon)
                    pauseIcon.isVisible = false
                    setOnMouseClicked {
                        onPlayListPlayPauseClicked(playlist, pauseIcon, playIcon)
                    }
                }
            }
            //name
            alignment = Pos.CENTER
            label(playlist.name) {
                alignment = Pos.CENTER
                textAlignment = TextAlignment.CENTER
                isWrapText = true
                addClass(playlistLabelStyle)
            }
        }
    }


    override fun renderAccountPlaylists(listOfPlaylist: List<Playlist>) {
        yourPlaylistsHBox.children.removeAll { true }
        for (playlist in listOfPlaylist) {
            yourPlaylistsHBox.createOnePlaylistBox(playlist)
        }
    }

    override var sectionTitle = "Home"

    //views
    lateinit var popularPlaylistsBox: HBox
    lateinit var yourPlaylistsHBox: HBox
    lateinit var popScroll: ScrollPane
    lateinit var yourScroll: ScrollPane


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
            isFitToWidth = true
            addClass(NewsViewStyles.mainScrollViewStyle)


            val initialInc = 100.0
            val skin = ScrollPaneSkin(this)
            skin.verticalScrollBar.unitIncrement = initialInc
            skin.verticalScrollBar.blockIncrement = initialInc
            this.skin = skin


            vbox {
                addClass(NewsViewStyles.mainVBoxStyle)
                label("Popular playlists") {
                    addClass(titleLabelStyle)
                }
                popScroll = scrollpane {
                    isFitToWidth = true
                    isFitToHeight = true
                    addClass(NewsViewStyles.mainScrollViewStyle)
                    setOnScroll {
                        //it.consume()
                        popScroll.hvalue += it.deltaY / (4 * it.multiplierY)
                    }
                    popularPlaylistsBox = hbox {
                        isFillHeight = true
                        isFitToWidth = true
                        addClass(HomeViewStyles.popularHBoxStyle)
                    }
                }
                label("Your playlists") {
                    addClass(titleLabelStyle)
                }

                yourScroll = scrollpane {
                    isFitToWidth = true
                    isFitToHeight = true
                    addClass(NewsViewStyles.mainScrollViewStyle)
                    setOnScroll {
                        //it.consume()
                        yourScroll.hvalue += it.deltaY / (4 * it.multiplierY)
                    }
                    yourPlaylistsHBox = hbox {
                        isFillHeight = true
                        isFitToWidth = true
                        addClass(HomeViewStyles.popularHBoxStyle)
                    }
                }
                vbox {
                    button("Create new playlist") {
                        addClass(AccountViewStyles.buttonDefaultStyle)
                        padding = insets(10)
                        prefHeight = 40.0
                        setOnMouseClicked {
                            openInternalWindow(
                                PlaylistCreateMessage(homePresenter),
                                owner = this.parent.parent.parent.parent.parent.parent
                            )
                        }
                    }
                    padding = insets(50)
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


    fun onPlayListPlayPauseClicked(
        playlist: Playlist, pauseIcon: SVGIcon,
        playIcon: SVGIcon
    ) {
        if (playIcon.isVisible) {
            playIcon.isVisible = false
            pauseIcon.isVisible = true
            pauseIcon.parent.toFront()
            homePresenter.onPlayPlaylistClicked(playlist)
        } else {
            playIcon.isVisible = true
            pauseIcon.isVisible = false
            playIcon.parent.toFront()
            homePresenter.onPausePlaylistClicked(playlist)
        }

    }

}