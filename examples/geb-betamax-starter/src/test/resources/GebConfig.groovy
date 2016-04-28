import org.openqa.selenium.Proxy
import org.openqa.selenium.Proxy.ProxyType
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

driver = {
    DesiredCapabilities capability = new DesiredCapabilities()
    Proxy proxy = new Proxy()
    proxy.setProxyType(ProxyType.MANUAL)
    proxy.setHttpProxy('http://127.0.0.1:15050')
    capability.setCapability(CapabilityType.PROXY, proxy)
    capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
    new FirefoxDriver(capability)
}

// driver = 'htmlunit'
// driver = 'ie'
// driver = 'chrome'