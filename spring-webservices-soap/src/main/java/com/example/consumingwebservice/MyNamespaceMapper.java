package com.example.consumingwebservice;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public class MyNamespaceMapper extends NamespacePrefixMapper {

	private static final String V1_PREFIX = "v1"; // DEFAULT NAMESPACE
	private static final String V1_URI = "http://www.uri.com.br/v1";

	private static final String V11_PREFIX = "v11";
	private static final String V11_URI = "http://www.uri.com.br/v11";

	@Override
	public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
		if (V1_URI.equals(namespaceUri)) {
			return V1_PREFIX;
		} else if (V11_URI.equals(namespaceUri)) {
			return V11_PREFIX;
		}
		return suggestion;
	}

	@Override
	public String[] getPreDeclaredNamespaceUris() {
		return new String[] { V1_URI, V11_URI };
	}
}