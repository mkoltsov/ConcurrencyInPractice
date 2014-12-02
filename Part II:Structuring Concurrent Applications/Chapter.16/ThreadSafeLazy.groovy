import sun.misc.Resource

class SafeLazy {
    private static Resource resource

    public synchronized static Resource getInstance() {
        if (resource == null) {
            resource = new Resource() {
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
        return resource
    }
}