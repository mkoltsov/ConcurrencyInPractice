import sun.misc.Resource

class ResourceFactory {
    private static class ResourceHolder {
        public static Resource resource = new Resource() {
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
    }

    public static Resource getResource() {
        return ResourceHolder.resource
    }
}