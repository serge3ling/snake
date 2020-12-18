/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Snake;

import java.util.Objects;

/**
 *
 * @author tret
 */
public class Record implements Comparable<Record> {
    String user;
    int points;
    
    public Record(String user, int points) {
        this.user = user;
        this.points = points;
    }
    
    @Override
    public int compareTo(Record record2) {
        int diff = record2.points - this.points;
        if (diff == 0) {
            diff = this.user.compareTo(record2.user);
        }
        return diff;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Record)) {
            return false;
        }
        
        Record record2 = (Record) obj;
        
        return (this.user.equals(record2.user) &&
                (this.points == record2.points));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.user);
        hash = 89 * hash + this.points;
        return hash;
    }
}
