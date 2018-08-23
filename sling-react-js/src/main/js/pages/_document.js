import Document, { Head, Main, NextScript } from 'next/document'

export default class MyDocument extends Document {
    static async getInitialProps(ctx) {
        const initialProps = await Document.getInitialProps(ctx);
        return { ...initialProps }
    }

    render() {
        return (
            <html>
            <Head>
                <link rel="stylesheet" href="/_next/static/style.css" />
            </Head>
            <body>
                <Main />
                <NextScript />
            </body>
            </html>
        )
    }
}