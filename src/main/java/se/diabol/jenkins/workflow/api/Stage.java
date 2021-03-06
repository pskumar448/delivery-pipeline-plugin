/*
This file is part of Delivery Pipeline Plugin.

Delivery Pipeline Plugin is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Delivery Pipeline Plugin is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Delivery Pipeline Plugin.
If not, see <http://www.gnu.org/licenses/>.
*/
package se.diabol.jenkins.workflow.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.joda.time.DateTime;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stage {

    public final Map<String, ?> _links;
    public final String id;
    public final String name;
    public final String status;
    public final DateTime startTimeMillis;
    public final Long durationMillis;

    public Stage(@JsonProperty("_links") Map<String, ?> _links,
                 @JsonProperty("id") String id,
                 @JsonProperty("name") String name,
                 @JsonProperty("status") String status,
                 @JsonProperty("startTimeMillis") DateTime startTimeMillis,
                 @JsonProperty("durationMillis") Long durationMillis) {
        this._links = _links;
        this.id = id;
        this.name = name;
        this.status = status;
        this.startTimeMillis = startTimeMillis;
        this.durationMillis = durationMillis;
    }

    public static long getDurationOfStageFromRun(Run previousRun, Stage currentStage) {
        Stage previouslyRunStage = previousRun.getStageByName(currentStage.name);
        if (previouslyRunStage == null || previouslyRunStage.durationMillis == null) {
            return -1L;
        }
        return previouslyRunStage.durationMillis;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Stage stage = (Stage) other;
        return Objects.equal(_links, stage._links)
                && Objects.equal(id, stage.id)
                && Objects.equal(name, stage.name)
                && Objects.equal(status, stage.status)
                && Objects.equal(startTimeMillis, stage.startTimeMillis)
                && Objects.equal(durationMillis, stage.durationMillis);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(_links, id, name, status, startTimeMillis, durationMillis);
    }

    @Override
    public String toString() {
        return "Stage{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", status='" + status + '\''
                + '}';
    }
}
