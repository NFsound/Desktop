package presentation.sections.music

import javafx.geometry.Pos
import javafx.scene.control.ScrollPane
import javafx.scene.text.TextAlignment
import javafx.stage.FileChooser
import presentation.presenters.sections.MusicPresenter
import presentation.presenters.sections.SectionPresenter
import presentation.styles.sections.AccountViewStyles
import presentation.styles.sections.MusicViewStyles
import presentation.styles.sections.NewsViewStyles
import tornadofx.*

class MusicViewImpl() : View(), MusicView {

    override var sectionTitle = "Music"

    override fun setPresenter(presenter: SectionPresenter) {
        musicPresenter = presenter as MusicPresenter
        musicPresenter.onInitialLoad()
    }

    override fun getPresenter(): SectionPresenter {
        return musicPresenter
    }

    private lateinit var musicPresenter: MusicPresenter

    override val root: ScrollPane = scrollpane {
        addClass(NewsViewStyles.mainScrollViewStyle)
        isFitToWidth = true
        isFitToHeight = true
        vbox {
            addClass(NewsViewStyles.mainVBoxStyle)
            vbox {
                alignment = Pos.TOP_CENTER
                label("Future has come. Generate your music here and now") {
                    textAlignment = TextAlignment.CENTER
                    addClass(MusicViewStyles.topLabelStyle)
                }
            }
            hbox {
                vbox {

                    label("Easy as one-two-three:") {
                        addClass(MusicViewStyles.smallLabelStyle)
                    }

                    hbox {
                        alignment = Pos.CENTER
                        label("1) Load your favourite composition") {
                            addClass(MusicViewStyles.smallLabelStyle)
                        }
                        button("Load file") {
                            addClass(MusicViewStyles.buttonMainDefaultStyle)
                            action {
                                val extFilter = FileChooser.ExtensionFilter("Music files (*.wav)", "*.wav")
                                chooseFile("Choose audio file", arrayOf(extFilter), mode = FileChooserMode.Single)
                            }
                        }



                    }

                    hbox {
                        label("2) Choose your parameters") {
                            addClass(MusicViewStyles.smallLabelStyle)
                        }
                        combobox(values = listOf("ecdc","dcs")) {

                        }
                    }
                    hbox {
                        label("3) Enjoy generated music") {
                            addClass(MusicViewStyles.smallLabelStyle)
                        }


                    }
                }
            }


        }
    }

}