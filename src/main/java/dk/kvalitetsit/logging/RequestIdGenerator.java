package dk.kvalitetsit.logging;

public interface RequestIdGenerator {
    /**
     * Returns the request id. If @customRequestId have been called that one is used. Otherwise the configured HTTP
     * header is checked and if header not found a random request id is generated.
     * @return The request id.
     */
    String getOrGenerateRequestId();
}
