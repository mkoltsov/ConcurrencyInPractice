import sun.misc.Resource

class EagerInit {
    private static Resource resource = new Resource() {
        @Override
        String getName() {
            return null
        }

        @Override
        URL getURL() {
            return null
        }

        @Override
        URL getCodeSourceURL() {
            return null
        }

        @Override
        InputStream getInputStream() throws IOException {
            return null
        }

        @Override
        int getContentLength() throws IOException {
            return 0
        }
    }

    public static Resource getInstance() {
        return resource
    }
}