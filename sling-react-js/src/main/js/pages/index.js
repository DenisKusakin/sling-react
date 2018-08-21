import AemPublishRootComponent from '../aem-poc/components/aem-publish-component';
import data from '../content/test/page.json';

export default () => <div>
    <link rel="stylesheet" href="/_next/static/style.css" />
    <AemPublishRootComponent config={data}/>
</div>