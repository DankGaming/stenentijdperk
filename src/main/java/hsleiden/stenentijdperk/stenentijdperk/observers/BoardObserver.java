package hsleiden.stenentijdperk.stenentijdperk.observers;

public interface BoardObserver {
    void update(BoardObservable boardobserver);

    void updatePunten(BoardObservable boardobserver);

    void removeKaart(BoardObservable boardobserver, int index);

    void removeHut(BoardObservable boardobserver, int index);
}
