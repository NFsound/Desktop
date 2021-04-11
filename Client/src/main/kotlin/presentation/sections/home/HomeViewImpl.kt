package presentation.sections.home

import javafx.event.EventHandler
import javafx.scene.control.ScrollPane
import javafx.scene.input.ScrollEvent
import javafx.scene.layout.*
import models.core.Playlist
import presentation.presenters.sections.HomePresenter
import presentation.presenters.sections.SectionPresenter
import presentation.styles.Colors
import presentation.styles.sections.HomeViewStyles
import presentation.styles.sections.HomeViewStyles.Companion.imagePLaylistStyle
import presentation.styles.sections.HomeViewStyles.Companion.playListStyle
import presentation.styles.sections.HomeViewStyles.Companion.titleLabelStyle
import presentation.styles.sections.NewsViewStyles
import tornadofx.*
import utils.ImageProvider


class HomeViewImpl : View(), HomeView {
    override fun renderPopularPlaylists(listOfPlaylist: List<Playlist>) {
        popularPlaylistsBox.children.removeAll { true }
        for (playlist in listOfPlaylist) {
            popularPlaylistsBox.stackpane {
                addClass(playListStyle)
                usePrefWidth = true
                usePrefHeight = true
                imageview(ImageProvider.getImage(playlist.image)) {
                    addClass(imagePLaylistStyle)
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
                        isFitToWidth = true
                        isFillHeight = true
                        addClass(HomeViewStyles.popularHBoxStyle)
                    }
                }
            }
        }


}