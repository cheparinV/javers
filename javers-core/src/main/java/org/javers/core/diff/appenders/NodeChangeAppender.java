package org.javers.core.diff.appenders;

import java.util.Set;

import org.javers.core.diff.Change;
import org.javers.core.diff.GraphPair;
import org.javers.model.object.graph.ObjectNode;

/**
 * Node scope change appender (NewObject & ObjectRemoved)
 */
public interface NodeChangeAppender {

   Set<Change> getChangeSet(GraphPair graphPair);

}
