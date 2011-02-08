#ifndef __X10_UTIL_ABSTRACTCOLLECTION_H
#define __X10_UTIL_ABSTRACTCOLLECTION_H

#include <x10rt.h>


#define X10_UTIL_ABSTRACTCONTAINER_H_NODEPS
#include <x10/util/AbstractContainer.h>
#undef X10_UTIL_ABSTRACTCONTAINER_H_NODEPS
#define X10_UTIL_COLLECTION_H_NODEPS
#include <x10/util/Collection.h>
#undef X10_UTIL_COLLECTION_H_NODEPS
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class Container;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace util { 

template<class FMGL(T)> class AbstractCollection;
template <> class AbstractCollection<void>;
template<class FMGL(T)> class AbstractCollection : public x10::util::AbstractContainer<FMGL(T)>
  {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    virtual x10_boolean add(FMGL(T) id__148) = 0;
    virtual x10_boolean remove(FMGL(T) id__149) = 0;
    virtual x10_boolean addAll(x10aux::ref<x10::util::Container<FMGL(T)> > c);
    virtual x10_boolean retainAll(x10aux::ref<x10::util::Container<FMGL(T)> > c);
    virtual x10_boolean removeAll(x10aux::ref<x10::util::Container<FMGL(T)> > c);
    virtual x10_boolean addAllWhere(x10aux::ref<x10::util::Container<FMGL(T)> > c,
                                    x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> > p);
    virtual x10_boolean removeAllWhere(x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> > p);
    virtual void clear();
    virtual x10aux::ref<x10::util::Container<FMGL(T)> > clone(
      ) = 0;
    virtual x10aux::ref<x10::util::AbstractCollection<FMGL(T)> >
      x10__util__AbstractCollection____x10__util__AbstractCollection__this(
      );
    void _constructor();
    
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class AbstractCollection<void> : public x10::util::AbstractContainer<void>
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_UTIL_ABSTRACTCOLLECTION_H

namespace x10 { namespace util { 
template<class FMGL(T)>
class AbstractCollection;
} } 

#ifndef X10_UTIL_ABSTRACTCOLLECTION_H_NODEPS
#define X10_UTIL_ABSTRACTCOLLECTION_H_NODEPS
#include <x10/util/AbstractContainer.h>
#include <x10/util/Collection.h>
#include <x10/lang/Boolean.h>
#include <x10/util/Container.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Iterator.h>
#include <x10/lang/Fun_0_1.h>
#ifndef X10_UTIL_ABSTRACTCOLLECTION__CLOSURE__0_CLOSURE
#define X10_UTIL_ABSTRACTCOLLECTION__CLOSURE__0_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
template<class FMGL(T)> class x10_util_AbstractCollection__closure__0 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_1<FMGL(T), x10_boolean>::template itable <x10_util_AbstractCollection__closure__0<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean __apply(FMGL(T) id__150) {
        
        //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10Return_c
        return true;
        
    }
    
    // captured environment
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_AbstractCollection__closure__0<FMGL(T) >* storage = x10aux::alloc<x10_util_AbstractCollection__closure__0<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_util_AbstractCollection__closure__0<FMGL(T) > >(storage));
        x10aux::ref<x10_util_AbstractCollection__closure__0<FMGL(T) > > this_ = new (storage) x10_util_AbstractCollection__closure__0<FMGL(T) >();
        return this_;
    }
    
    x10_util_AbstractCollection__closure__0() { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10:19";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_1<FMGL(T), x10_boolean>::template itable <x10_util_AbstractCollection__closure__0<FMGL(T) > >x10_util_AbstractCollection__closure__0<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_AbstractCollection__closure__0<FMGL(T) >::__apply, &x10_util_AbstractCollection__closure__0<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_util_AbstractCollection__closure__0<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >, &x10_util_AbstractCollection__closure__0<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_util_AbstractCollection__closure__0<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_AbstractCollection__closure__0<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_UTIL_ABSTRACTCOLLECTION__CLOSURE__0_CLOSURE
#ifndef X10_UTIL_ABSTRACTCOLLECTION__CLOSURE__1_CLOSURE
#define X10_UTIL_ABSTRACTCOLLECTION__CLOSURE__1_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
template<class FMGL(T)> class x10_util_AbstractCollection__closure__1 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_1<FMGL(T), x10_boolean>::template itable <x10_util_AbstractCollection__closure__1<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean __apply(FMGL(T) x) {
        
        //#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10Return_c
        return !(x10::util::Container<FMGL(T)>::contains(x10aux::nullCheck(c), 
                   x));
        
    }
    
    // captured environment
    x10aux::ref<x10::util::Container<FMGL(T)> > c;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->c);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_AbstractCollection__closure__1<FMGL(T) >* storage = x10aux::alloc<x10_util_AbstractCollection__closure__1<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_util_AbstractCollection__closure__1<FMGL(T) > >(storage));
        x10aux::ref<x10::util::Container<FMGL(T)> > that_c = buf.read<x10aux::ref<x10::util::Container<FMGL(T)> > >();
        x10aux::ref<x10_util_AbstractCollection__closure__1<FMGL(T) > > this_ = new (storage) x10_util_AbstractCollection__closure__1<FMGL(T) >(that_c);
        return this_;
    }
    
    x10_util_AbstractCollection__closure__1(x10aux::ref<x10::util::Container<FMGL(T)> > c) : c(c) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10:20";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_1<FMGL(T), x10_boolean>::template itable <x10_util_AbstractCollection__closure__1<FMGL(T) > >x10_util_AbstractCollection__closure__1<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_AbstractCollection__closure__1<FMGL(T) >::__apply, &x10_util_AbstractCollection__closure__1<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_util_AbstractCollection__closure__1<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >, &x10_util_AbstractCollection__closure__1<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_util_AbstractCollection__closure__1<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_AbstractCollection__closure__1<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_UTIL_ABSTRACTCOLLECTION__CLOSURE__1_CLOSURE
#ifndef X10_UTIL_ABSTRACTCOLLECTION__CLOSURE__2_CLOSURE
#define X10_UTIL_ABSTRACTCOLLECTION__CLOSURE__2_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
template<class FMGL(T)> class x10_util_AbstractCollection__closure__2 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_1<FMGL(T), x10_boolean>::template itable <x10_util_AbstractCollection__closure__2<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean __apply(FMGL(T) x) {
        
        //#line 21 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10Return_c
        return x10::util::Container<FMGL(T)>::contains(x10aux::nullCheck(c), 
                 x);
        
    }
    
    // captured environment
    x10aux::ref<x10::util::Container<FMGL(T)> > c;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->c);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_AbstractCollection__closure__2<FMGL(T) >* storage = x10aux::alloc<x10_util_AbstractCollection__closure__2<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_util_AbstractCollection__closure__2<FMGL(T) > >(storage));
        x10aux::ref<x10::util::Container<FMGL(T)> > that_c = buf.read<x10aux::ref<x10::util::Container<FMGL(T)> > >();
        x10aux::ref<x10_util_AbstractCollection__closure__2<FMGL(T) > > this_ = new (storage) x10_util_AbstractCollection__closure__2<FMGL(T) >(that_c);
        return this_;
    }
    
    x10_util_AbstractCollection__closure__2(x10aux::ref<x10::util::Container<FMGL(T)> > c) : c(c) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10:21";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_1<FMGL(T), x10_boolean>::template itable <x10_util_AbstractCollection__closure__2<FMGL(T) > >x10_util_AbstractCollection__closure__2<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_AbstractCollection__closure__2<FMGL(T) >::__apply, &x10_util_AbstractCollection__closure__2<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_util_AbstractCollection__closure__2<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >, &x10_util_AbstractCollection__closure__2<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_util_AbstractCollection__closure__2<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_AbstractCollection__closure__2<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_UTIL_ABSTRACTCOLLECTION__CLOSURE__2_CLOSURE
#ifndef X10_UTIL_ABSTRACTCOLLECTION__CLOSURE__3_CLOSURE
#define X10_UTIL_ABSTRACTCOLLECTION__CLOSURE__3_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
template<class FMGL(T)> class x10_util_AbstractCollection__closure__3 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_1<FMGL(T), x10_boolean>::template itable <x10_util_AbstractCollection__closure__3<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean __apply(FMGL(T) id__153) {
        
        //#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10Return_c
        return true;
        
    }
    
    // captured environment
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_AbstractCollection__closure__3<FMGL(T) >* storage = x10aux::alloc<x10_util_AbstractCollection__closure__3<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_util_AbstractCollection__closure__3<FMGL(T) > >(storage));
        x10aux::ref<x10_util_AbstractCollection__closure__3<FMGL(T) > > this_ = new (storage) x10_util_AbstractCollection__closure__3<FMGL(T) >();
        return this_;
    }
    
    x10_util_AbstractCollection__closure__3() { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10:41";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_1<FMGL(T), x10_boolean>::template itable <x10_util_AbstractCollection__closure__3<FMGL(T) > >x10_util_AbstractCollection__closure__3<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_AbstractCollection__closure__3<FMGL(T) >::__apply, &x10_util_AbstractCollection__closure__3<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_util_AbstractCollection__closure__3<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >, &x10_util_AbstractCollection__closure__3<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_util_AbstractCollection__closure__3<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_AbstractCollection__closure__3<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_UTIL_ABSTRACTCOLLECTION__CLOSURE__3_CLOSURE
#ifndef X10_UTIL_ABSTRACTCOLLECTION_H_GENERICS
#define X10_UTIL_ABSTRACTCOLLECTION_H_GENERICS
#endif // X10_UTIL_ABSTRACTCOLLECTION_H_GENERICS
#ifndef X10_UTIL_ABSTRACTCOLLECTION_H_IMPLEMENTATION
#define X10_UTIL_ABSTRACTCOLLECTION_H_IMPLEMENTATION
#include <x10/util/AbstractCollection.h>


template<class FMGL(T)> void x10::util::AbstractCollection<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::AbstractCollection<FMGL(T)>");
    
}


//#line 16 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10MethodDecl_c

//#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10MethodDecl_c

//#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::util::AbstractCollection<FMGL(T)>::addAll(
  x10aux::ref<x10::util::Container<FMGL(T)> > c) {
    
    //#line 19 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::AbstractCollection<FMGL(T)> >)this)->addAllWhere(
             c,
             x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> > >(x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >(x10aux::ref<x10_util_AbstractCollection__closure__0<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >(sizeof(x10_util_AbstractCollection__closure__0<FMGL(T)>)))x10_util_AbstractCollection__closure__0<FMGL(T)>()))));
    
}

//#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::util::AbstractCollection<FMGL(T)>::retainAll(
  x10aux::ref<x10::util::Container<FMGL(T)> > c) {
    
    //#line 20 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::AbstractCollection<FMGL(T)> >)this)->removeAllWhere(
             x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> > >(x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >(x10aux::ref<x10_util_AbstractCollection__closure__1<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >(sizeof(x10_util_AbstractCollection__closure__1<FMGL(T)>)))x10_util_AbstractCollection__closure__1<FMGL(T)>(c)))));
    
}

//#line 21 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::util::AbstractCollection<FMGL(T)>::removeAll(
  x10aux::ref<x10::util::Container<FMGL(T)> > c) {
    
    //#line 21 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::AbstractCollection<FMGL(T)> >)this)->removeAllWhere(
             x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> > >(x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >(x10aux::ref<x10_util_AbstractCollection__closure__2<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >(sizeof(x10_util_AbstractCollection__closure__2<FMGL(T)>)))x10_util_AbstractCollection__closure__2<FMGL(T)>(c)))));
    
}

//#line 23 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::util::AbstractCollection<FMGL(T)>::addAllWhere(
  x10aux::ref<x10::util::Container<FMGL(T)> > c,
  x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> > p) {
    
    //#line 24 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10LocalDecl_c
    x10_boolean result = false;
    
    //#line 25 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": polyglot.ast.For_c
    {
        x10aux::ref<x10::lang::Iterator<FMGL(T)> > x2070;
        for (
             //#line 25 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10LocalDecl_c
             x2070 = x10::util::Container<FMGL(T)>::iterator(x10aux::nullCheck(c));
             x10::lang::Iterator<FMGL(T)>::hasNext(x10aux::nullCheck(x2070));
             ) {
            
            //#line 25 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10LocalDecl_c
            FMGL(T) x = x10::lang::Iterator<FMGL(T)>::next(x10aux::nullCheck(x2070));
            
            //#line 26 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10If_c
            if (x10::lang::Fun_0_1<FMGL(T), x10_boolean>::__apply(x10aux::nullCheck(p), 
                  x)) {
                
                //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": polyglot.ast.Eval_c
                result = ((x10_boolean) (((result) ? 1 : 0) | ((((x10aux::ref<x10::util::AbstractCollection<FMGL(T)> >)this)->add(
                                                                  x)) ? 1 : 0)));
            }
            
        }
    }
    
    //#line 29 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10Return_c
    return result;
    
}

//#line 32 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::util::AbstractCollection<FMGL(T)>::removeAllWhere(
  x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> > p) {
    
    //#line 33 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10LocalDecl_c
    x10_boolean result = false;
    
    //#line 34 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": polyglot.ast.For_c
    {
        x10aux::ref<x10::lang::Iterator<FMGL(T)> > x2072;
        for (
             //#line 34 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10LocalDecl_c
             x2072 = x10::util::Collection<FMGL(T)>::iterator(x10aux::nullCheck(static_cast<x10aux::ref<x10::util::Collection<FMGL(T)> > >(((x10aux::ref<x10::util::AbstractCollection<FMGL(T)> >)this)->clone())));
             x10::lang::Iterator<FMGL(T)>::hasNext(x10aux::nullCheck(x2072));
             ) {
            
            //#line 34 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10LocalDecl_c
            FMGL(T) x = x10::lang::Iterator<FMGL(T)>::next(x10aux::nullCheck(x2072));
            
            //#line 35 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10If_c
            if (x10::lang::Fun_0_1<FMGL(T), x10_boolean>::__apply(x10aux::nullCheck(p), 
                  x)) {
                
                //#line 36 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": polyglot.ast.Eval_c
                result = ((x10_boolean) (((result) ? 1 : 0) | ((((x10aux::ref<x10::util::AbstractCollection<FMGL(T)> >)this)->remove(
                                                                  x)) ? 1 : 0)));
            }
            
        }
    }
    
    //#line 38 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10Return_c
    return result;
    
}

//#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::util::AbstractCollection<FMGL(T)>::clear(
  ) {
    
    //#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::AbstractCollection<FMGL(T)> >)this)->removeAllWhere(
      x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> > >(x10aux::ref<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >(x10aux::ref<x10_util_AbstractCollection__closure__3<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_1<FMGL(T), x10_boolean> >(sizeof(x10_util_AbstractCollection__closure__3<FMGL(T)>)))x10_util_AbstractCollection__closure__3<FMGL(T)>()))));
}

//#line 43 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10MethodDecl_c

//#line 15 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::util::AbstractCollection<FMGL(T)> >
  x10::util::AbstractCollection<FMGL(T)>::x10__util__AbstractCollection____x10__util__AbstractCollection__this(
  ) {
    
    //#line 15 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::AbstractCollection<FMGL(T)> >)this);
    
}

//#line 15 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/AbstractCollection.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::util::AbstractCollection<FMGL(T)>::_constructor(
                          ) {
    this->::x10::util::AbstractContainer<FMGL(T)>::_constructor();
    {
     
    }
    
}

template<class FMGL(T)> void x10::util::AbstractCollection<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::util::AbstractContainer<FMGL(T)>::_serialize_body(buf);
    
}

template<class FMGL(T)> void x10::util::AbstractCollection<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::util::AbstractContainer<FMGL(T)>::_deserialize_body(buf);
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::util::AbstractCollection<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::util::AbstractCollection<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::AbstractCollection<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::util::AbstractContainer<FMGL(T)> >(), x10aux::getRTT<x10::util::Collection<FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.AbstractCollection";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 2, parents, 1, params, variances);
}
#endif // X10_UTIL_ABSTRACTCOLLECTION_H_IMPLEMENTATION
#endif // __X10_UTIL_ABSTRACTCOLLECTION_H_NODEPS
