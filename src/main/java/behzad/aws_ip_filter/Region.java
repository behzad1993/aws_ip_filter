package behzad.aws_ip_filter;

public enum Region {
    ALL("all"),
    EU("eu"),
    US("us"),
    AP("ap"),
    CN("cn"),
    SA("sa"),
    AF("af"),
    CA("ca");

    public final String region;

    Region(String region) {
        this.region = region;
    }
}
