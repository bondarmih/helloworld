package bondarmih.edu.persistence.serializer.binary;

import bondarmih.edu.catalog.Album;
import bondarmih.edu.catalog.Artist;
import bondarmih.edu.catalog.Catalog;
import bondarmih.edu.catalog.Track;
import bondarmih.edu.persistence.dataobjects.AlbumDataObject;
import bondarmih.edu.persistence.dataobjects.ArtistDataObject;
import bondarmih.edu.persistence.dataobjects.CatalogDataObject;
import bondarmih.edu.persistence.dataobjects.TrackDataObject;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by bondarm on 11.06.16.
 */
public class CatalogDataCollector {

    public CatalogDataObject toCatalogDO(Catalog catalog) {
        List<ArtistDataObject> artists = catalog
                .getArtists()
                .stream()
                .map(this::toArtistDO)
                .collect(Collectors.toList());
        return new CatalogDataObject(artists);
    }

    private ArtistDataObject toArtistDO(Artist artist) {
        List<AlbumDataObject> albums = artist
                .getAlbums()
                .stream()
                .map(this::toAlbumDO)
                .collect(Collectors.toList());
        return new ArtistDataObject(artist.getName(), albums);
    }

    private AlbumDataObject toAlbumDO(Album album) {
        List<TrackDataObject> trackList = album
                .getTracklist()
                .stream()
                .map(this::toTrackDO)
                .collect(Collectors.toList());
        return new AlbumDataObject(album.getName(), album.getGenre(), trackList);
    }

    private TrackDataObject toTrackDO(Track track) {
        return new TrackDataObject(track.getName(), track.getLength());
    }

    public Catalog toCatalog(CatalogDataObject catalogDO) {
        List<Artist> artists = catalogDO
                .getArtists()
                .stream()
                .map(this::toArtist)
                .collect(Collectors.toList());
        return new Catalog(artists);
    }

    private Artist toArtist(ArtistDataObject artistDO) {
        List<Album> albums = artistDO
                .getAlbums()
                .stream()
                .map(this::toAlbum)
                .collect(Collectors.toList());
        return new Artist(artistDO.getName(), albums);
    }

    private Album toAlbum(AlbumDataObject albumDO) {
        List<Track> trackList = albumDO
                .getTracks()
                .stream()
                .map(this::toTrack)
                .collect(Collectors.toList());
        return new Album(albumDO.getName(), albumDO.getGenre(), trackList);
    }

    private Track toTrack(TrackDataObject trackDO) {
        return new Track(trackDO.getName(), trackDO.getLength());
    }
}
