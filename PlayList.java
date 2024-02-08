/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /**
     * Constructs an empty play list with a maximum number of tracks.
     */
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /**
     * Returns the maximum size of this play list.
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Returns the current number of tracks in this play list.
     */
    public int getSize() {
        return size;
    }

    /**
     * Method to get a track by index
     */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }

    /**
     * Appends the given track to the end of this list.
     * If the list is full, does nothing and returns false.
     * Otherwise, appends the track and returns true.
     */
    public boolean add(Track track) {
        //// replace the following statement with your code
        boolean ans;
        if (getSize() == getMaxSize()) ans = false;
        else {
            ans = true;
            tracks[size] = track;
            size = size + 1;
        }
        return ans;
    }

    /**
     * Returns the data of this list, as a string. Each track appears in a separate line.
     */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        //// replace the following statement with your code
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < size; i++) {
        answer.append(getTrack(i).toString());
        answer.append("\n");
        }
        return answer.toString();
    }

    /**
     * Removes the last track from this list. If the list is empty, does nothing.
     */
    public void removeLast() {
        //// replace this comment with your code
        if (size > 0) {
            tracks[size] = null;
            size = size - 1;
        }
    }

    /**
     * Returns the total duration (in seconds) of all the tracks in this list.
     */
    public int totalDuration() {
        //// replace the following statement with your code
        int total = 0;
        for (int i = 0; i < size; i++) {
            total = total + tracks[i].getDuration();
        }
        return total;
    }

    /**
     * Returns the index of the track with the given title in this list.
     * If such a track is not found, returns -1.
     */
    public int indexOf(String title) {
        int index = -1;
        //// replace the following statement with your code
        for (int i = 0; i < size; i++) {
            if (tracks[i].getTitle().equals(title)) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Inserts the given track in index i of this list. For example, if the list is
     * (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     * If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     * If i is negative or greater than the size of this list, or if the list
     * is full, does nothing and returns false. Otherwise, inserts the track and
     * returns true.
     */
    public boolean add(int i, Track track) {
        //// replace the following statement with your code
        boolean ans = false;
        if ((i < 0) || (i > size) || (size == maxSize)) ans = false;
             else {
                for (int j = size - 1; j >= i; j--) {
                    tracks[j+1] = tracks[j];
                }
                tracks[i] = track;
                size = size + 1;
                ans = true;
            }
        return ans;
    }

    /**
     * Removes the track in the given index from this list.
     * If the list is empty, or the given index is negative or too big for this list,
     * does nothing and returns -1.
     */
    public void remove(int i) {
        //// replace this comment with your code
        if(!(i<0 || i>=size || size==0)) {
            for(int j=i ; j<size-1 ; j++)
                tracks[j] = tracks[j+1];
            this.removeLast();
        }
    }

    /**
     * Removes the first track that has the given title from this list.
     * If such a track is not found, or the list is empty, or the given index
     * is negative or too big for this list, does nothing.
     */
    public void remove(String title) {
        //// replace this comment with your code
        if (size > 0) {
            for (int j = 0; j < size; j++) {
                if (tracks[j].getTitle() == title) {
                    remove(j);
                    break;
                }
            }
            size--;
        }
    }

    /**
     * Removes the first track from this list. If the list is empty, does nothing.
     */
    public void removeFirst() {
        //// replace this comment with your code
        if (size > 0) {
            remove(0);
        }
    }

    /**
     * Adds all the tracks in the other list to the end of this list.
     * If the total size of both lists is too large, does nothing.
     */
    //// An elegant and terribly inefficient implementation.
    public void add(PlayList other) {
        int j = 0;
        int i = size;

    //// replace this comment with your code
         if(size +other.getSize()<=maxSize)
    {
        while (i <= size + other.getSize()) {
                tracks[i] = other.tracks[j];
                j++;
                i++;
            }
        }
         size = i;
    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) {
        //// replace the following statement with your code
        if(start < 0 || start > size-1) return -1;
        int index = 0;
        int min = tracks[start].getDuration();
            for(int i = start; i < size-1; i++)
            {
                if(min > tracks[i].getDuration()) min = tracks[i].getDuration();
                index = i;
            }

        return index;
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        if(size == 0) return null;
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
        Track temp;
        int minIndex1;
        for(int i=0 ; i<size ; i++) {
            minIndex1 = minIndex(i);
            temp = tracks[i];
            tracks[i]=tracks[minIndex1];
            tracks[minIndex1] = temp;
        }
    }
}
