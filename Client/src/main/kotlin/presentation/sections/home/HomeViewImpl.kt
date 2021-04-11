package presentation.sections.home

import javafx.event.EventHandler
import javafx.scene.control.ScrollPane
import javafx.scene.input.ScrollEvent
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import models.core.Playlist
import presentation.presenters.sections.HomePresenter
import presentation.presenters.sections.SectionPresenter
import presentation.styles.sections.HomeViewStyles
import presentation.styles.sections.HomeViewStyles.Companion.playListStyle
import presentation.styles.sections.HomeViewStyles.Companion.titleLabelStyle
import tornadofx.*


class HomeViewImpl : View(), HomeView {
    override fun renderPopularPlaylists(listOfPlaylist: List<Playlist>) {
        popularPlaylistsBox.children.removeAll { true }
        for (i in 0..100) {
            popularPlaylistsBox.stackpane {
                addClass(playListStyle)
                usePrefWidth = true
                vbox {


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
            addClass(HomeViewStyles.homeHostStyle)
            vbox {
                addClass(HomeViewStyles.homeHostStyle)
                isFillWidth = true
                useMaxHeight = true
                label("Popular playlists") {
                    addClass(titleLabelStyle)
                }
                scrollpane {
                    addClass(HomeViewStyles.popularScrollViewStyle)
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