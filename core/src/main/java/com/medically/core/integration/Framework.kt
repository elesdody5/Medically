package com.medically.core.integration

import com.medically.core.downloader.DownLoaderPort
import com.medically.core.player.MusicServiceConnectionPort

object Framework {
    lateinit var musicServiceConnectionPort: MusicServiceConnectionPort
    lateinit var downLoaderManager: DownLoaderPort
}